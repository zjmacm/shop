package com.shop.dao.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 14-12-10.
 */
public class ThreadIndexWriter extends IndexWriter {

    private ExecutorService threadPool;

    private Analyzer defaultAnalyzer;

    public ThreadIndexWriter(Directory d, IndexWriterConfig conf,
                             int numThreads, int maxQueueSize) throws IOException {
        super(d, conf);
        defaultAnalyzer = new IKAnalyzer();
        threadPool = new ThreadPoolExecutor(numThreads, numThreads,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(maxQueueSize, false),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void addDocument(Document doc){
        threadPool.execute(new Job(doc, defaultAnalyzer, null));
    }

    public void addDocument(Document doc, Analyzer a){
        threadPool.execute(new Job(doc, a, null));
    }

    public void updateDocument(Term term, Document doc){
        threadPool.execute(new Job(doc, defaultAnalyzer, term));
    }

    public void updateDocument(Term term, Document doc, Analyzer a){
        threadPool.execute(new Job(doc, a, term));
    }

    private void finish(){
        threadPool.shutdown();
        while (true){
            try {
                if(threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)){
                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
    public void close() throws IOException {
        finish();
        super.close();
    }

    public void close(boolean doWait) throws IOException {
        finish();
        super.close(doWait);
    }

    public void rollback() throws IOException {
        finish();
        super.rollback();
    }

    private class Job implements Runnable{

        Document document;
        Analyzer analyzer;
        Term term;

        private Job(Document document, Analyzer analyzer, Term term) {
            this.document = document;
            this.analyzer = analyzer;
            this.term = term;
        }

        @Override
        public void run() {
            try {
                if (term != null) {
                    ThreadIndexWriter.super.updateDocument(term, document, analyzer);
                }else{
                    ThreadIndexWriter.super.addDocument(document, analyzer);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }


    }
}
