package com.shop.dao.lucene;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * Created by yj on 14-12-10.
 */
public class SearchManager {
    private IndexSearcher currentSearcher;

    private IndexWriter writer;

    private boolean reopening;

    public SearchManager(Directory directory) throws IOException {
        currentSearcher = new IndexSearcher(DirectoryReader.open(directory));
        warm(currentSearcher);
    }

    public SearchManager(IndexWriter writer) throws IOException {
        this.writer = writer;
        //从现有的writer中获取近实时的reader
        currentSearcher = new IndexSearcher(DirectoryReader.open(writer, true));
        warm(currentSearcher);
        //writer.set
    }

    //子类实现
    public void warm(IndexSearcher searcher) throws IOException{

    }


}
