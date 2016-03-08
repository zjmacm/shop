package com.shop.dao.test;

import com.shop.dao.impl.RedisDao;
import com.shop.dao.lucene.SearchEngin;
import com.shop.dao.lucene.SerialScoreDoc;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yj on 14-12-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class SearchTest {

    @Autowired
    SearchEngin engin;

    @Autowired
    RedisDao redisDao;

    //false 为升序
    Sort sort = new Sort(new SortField("price", SortField.Type.DOUBLE, false));

    @Test
    public void buildIndexTest(){

    }

    @Test
    public void IKTest() throws ParseException {
        QueryParser parser = new QueryParser(
                Version.LUCENE_46,"name",new IKAnalyzer());
        Query query = parser.parse("男士靴子");
        System.out.println(query.toString());
    }

    @Test
    public void searchTest() throws IOException {
        TopDocs t = engin.termSearch(null,"name", "魅族",new Sort(), Integer.MAX_VALUE, null);        System.out.println("page");
        out(t);
    }

    @Test
    public void parseSearchTest() throws IOException, ParseException {

        TopDocs t = engin.simpleSearch(null, "price","[500.0 TO 900.0]",sort, 5, null);
        out(t);
    }

    private void out(TopDocs t) throws IOException {
        ScoreDoc[] docs = t.scoreDocs;
        IndexReader reader = engin.getReader();
        for(ScoreDoc doc : docs){
            Document document = reader.document(doc.doc);
            System.out.println(document.toString());
        }
    }

    @Test
    public void booleanSearchTest() throws IOException {
        Map<Query,BooleanClause.Occur> queries = new HashMap<>();
        queries.put(
                new TermQuery(new Term("name","魅族")),
                BooleanClause.Occur.MUST);
        queries.put(
                NumericRangeQuery.newDoubleRange("price",500d,900d,true,true),
                BooleanClause.Occur.MUST);
        TopDocs t = engin.booleanSearch(null, queries,sort, 5, null);
        out(t);
    }


    @Test
    public void deleteTest() throws IOException {
       engin.delete("ff8080814a1f6bcc014a1f6bd3b40003");
        TopDocs t = engin.termSearch(null, "name", "魅族",sort, 6, null);
        out(t);
    }

    @Test
    public void updateTest() throws IOException, IllegalAccessException {
        Map<String, Object> info = new HashMap<>();
        info.put("id","ff8080814a1f6bcc014a1f6bd3b40003");
        info.put("name","华为");
        info.put("price",1000d);
        info.put("description","华为大降价");
        info.put("brand","国产");
        engin.updateById("ff8080814a1f6bcc014a1f6bd3b40003",info);
        TopDocs t = engin.termSearch(null, "brand", "国产",null, 10, null);
        out(t);

    }

    @Test
    public void multiFieldQuery() throws IOException, ParseException {
        String text = "高保温";
        String[] fields = {"name", "description"};
        BooleanClause.Occur[] occurs = {
                BooleanClause.Occur.SHOULD,
                BooleanClause.Occur.SHOULD};

        TopDocs t = engin.multiFieldQuery(null, text, null, 5, null, fields, occurs );
        out(t);
    }

    @Test
    public void pageTest() throws IOException {
        TopDocs t = engin.termSearch(null,"name", "魅族",null , 4, null);        System.out.println("page");
        System.out.println("page1");
        out(t);
        ScoreDoc[] docs = t.scoreDocs;
        redisDao.saveObject("search.page", new SerialScoreDoc().setScoreDoc(docs[docs.length - 1]));
        ScoreDoc page = ((SerialScoreDoc) redisDao.getObject("search.page")).getScoreDoc();

        System.out.println(page);
        t = engin.termSearch(page, "name", "魅族", null, 2, null);
        System.out.println("page2");
        out(t);
    }
}
