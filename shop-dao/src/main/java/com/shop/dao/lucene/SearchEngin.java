package com.shop.dao.lucene;

import com.shop.dao.GoodsDao;
import com.shop.dao.impl.RedisDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by yj on 14-12-9.
 *
 * 封装搜索的基础功能,基于Lucene实现
 */
@Component
public class SearchEngin {

    private GoodsDao goodsDao;

    private Log logger = LogFactory.getLog(this.getClass());

    private Directory directory;

    private IndexWriter writer;

    private IndexReader reader;

    private final String PATH = "index";

    @Autowired
    private RedisDao redisDao;

    public final String[] fields = new String[]{
            "shop_goods.id", "name", "price", "brand", "category","description"
    };
    /**
     * 从数据库加载数据构建索引
     *
     * */
    @Autowired
    public SearchEngin(GoodsDao goodsDao) throws IllegalAccessException {
        this.goodsDao = goodsDao;
        List<Map<String,Object>> list = goodsDao.getAllGoods(fields);
        if(list == null)
            throw new IllegalAccessException("请将商品数据录入数据库");
        initIndex(list);
    }

    private FieldType getStoreIndexTokenType(){
        FieldType type = new FieldType();
        type.setIndexed(true);
        type.setStored(true);
        type.setTokenized(true);
        return type;
    }

    private FieldType getUnTokenType(){
        FieldType type = new FieldType();
        type.setIndexed(true);
        type.setStored(true);
        type.setTokenized(false);
        return type;
    }

    /**
     * 初始化索引
     * @param data 要建立索引的数据链表
     * */
    private void initIndex(List<Map<String,Object>> data) throws IllegalAccessException {
        logger.info("init index on lucene");
        File indexDir = new File(PATH);
        if(!indexDir.exists()){
            indexDir.mkdir();
        }
        try {
            directory = FSDirectory.open(indexDir);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46,new IKAnalyzer());
            writer = new IndexWriter(directory,config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] files = indexDir.listFiles();
        if(files.length > 0)//启动时如果索引文件夹有索引文件 就不再从数据库建立索引
            return;
        //建立索引
        addIndex(data);
    }

    /**
     * 从list中的数据添加索引
     * */
    public void addIndex(List<Map<String, Object>> list) throws IllegalAccessException {
        assert (writer != null);
        for(Map<String,Object> row : list){
            Document doc = formDoc(row);
            try {
                writer.addDocument(doc);
            } catch (IOException e) {
                logger.error("cannot add document");
            }
        }
        try {
            writer.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从map集合生成Document文档对象
     * */
    private Document formDoc(Map<String, Object> info) throws IllegalAccessException {
        Document document = new Document();
        Set<String> keys = info.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String column = iterator.next();
            Object value = info.get(column);
            if(value == null){
                logger.error("cannot access the specified column");
                throw new IllegalAccessException("cannot access the specified column");
            }
            Type type = value.getClass();
            //域控制选项:存储数据,建立索引,不进行分词
            FieldType fieldType = getUnTokenType();

            if(type.equals(BigDecimal.class)){
                document.add(new DoubleField(column,Double.valueOf(value.toString()), Field.Store.YES));
            }else if (type.equals(double.class) || type.equals(Double.class)){
                document.add(new DoubleField(column,Double.valueOf(value.toString()), Field.Store.YES));
            }
            else {
                if(column.equals("description"))//对商品描述进行分词
                    fieldType = getStoreIndexTokenType();
                document.add(new Field(column, (String) value,fieldType));
            }
        }
        return document;
    }

    /**
     * 查询索引数量
     * @return 已建立的索引数量
     * */
    public int findIndexCount(){
        initReader();
        int total = reader.numDocs();
        logger.debug("total index count is " + total);
        return total;
    }


    /**
     * 根据商品的id删除其对应的索引
     * @param id 要删除的索引的商品id
     * @see com.shop.domain.Goods
     * */
    public void delete(String id){
        logger.debug("delete the doc whose id is " + id);
        Query query = new TermQuery(new Term("id",id));
        try {
            writer.deleteDocuments(query);
            writer.commit();
        } catch (IOException e) {
            logger.error("cannot delete the doc , check the id please");
            e.printStackTrace();
        }
    }

    /**
     * 所有操作共享一个IndexReader,使用近实时搜索
     * indexReader本身线程安全
     * */
    private void initReader(){
        if(reader == null){
            try {
                reader = DirectoryReader.open(writer, true);
            } catch (IOException e) {
                logger.error("cannot open index directory");
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新指定商品的索引信息
     * @param id 商品id
     * @param info 商品的信息(要和建立索引时使用的字段相同)
     * */
    public void updateById(String id, Map<String, Object> info) throws IllegalAccessException, IOException {
       Document doc = formDoc(info);
       writer.updateDocument(new Term("id", id), doc);
       writer.commit();
       logger.debug("update document!");
    }

    /**
     * 简单查询接口
     * @param after 用于分页
     * @param field 要查询的默认文档字段
     * @param searchText 查询语句
     * @param sort 排序规则
     * @param topN 要返回的查询结果数量
     * @param filter 过滤器
     *
     * 调用方法: simpleSearch("name", "男士靴子", 10)
     * 返回结果: 10条匹配程度最高的,索引域"name"中包含"男士","靴子"的商品数据
     *          searchText 会根据中文分词器进行分词
     * */
    public TopDocs simpleSearch(ScoreDoc after, String field, String searchText,Sort sort, int topN, Filter filter) throws ParseException, IOException {
        IndexSearcher searcher = new IndexSearcher(reader);
        QueryParser parser = new QueryParser(
                Version.LUCENE_46, field, new IKAnalyzer());

        Query query = parser.parse(searchText);
        return search(after, query, filter, topN, sort);
    }


    /**
     * 根据布尔查询条件组成查询语句 返回查询结果
     * @param queries 查询条件集 根据所有条件进行组合查询,
     *                query可以是Query的任何子类,BooleanClause是组合的逻辑词
     *                常见有:TermQuery NumericRangeQuery 更多请查询lucene文档
     * @param topN 要返回的查询结果数量
     * */
    public TopDocs booleanSearch(ScoreDoc after, Map<Query,BooleanClause.Occur> queries,
                                 Sort sort, int topN, Filter filter) throws IOException {
        BooleanQuery booleanQuery = new BooleanQuery();
        Set<Query> keys = queries.keySet();
        Iterator<Query> iterator = keys.iterator();
        while (iterator.hasNext()){
            Query q = iterator.next();
            booleanQuery.add(q, queries.get(q));
        }

        return  search(after, booleanQuery, filter, topN, sort);
    }

    /**
     * 根据指定域进行查询
     * @param after 上次查询的末尾,用于分页
     * @param field 域
     * @param searchText 域值
     * @param topN 要返回的查询结果数量
     * */
    public TopDocs termSearch(ScoreDoc after, String field, String searchText,
                              Sort sort, int topN, Filter filter) throws IOException {
        Query query = new TermQuery(new Term(field,searchText));
        return search(after, query, filter, topN, sort);
    }

    public IndexReader getReader(){
        initReader();
        return reader;
    }

    /**
     * 多域联合查询
     * @param queryText 查询域值
     * @param topN 要返回的结果数量
     * @param fields 查询域集合
     * @param occurs  各个域的逻辑谓词
     *
     * */
    public TopDocs multiFieldQuery(ScoreDoc after, String queryText, Filter filter, int topN, Sort sort,
                                   String[] fields, BooleanClause.Occur[] occurs) throws ParseException, IOException {
        Query query = MultiFieldQueryParser.parse(
                Version.LUCENE_46,
                queryText,
                fields, occurs, new IKAnalyzer());
        return search(after, query, filter, topN, sort);
    }

    /**
     * 底层查询方法
     * */
    private TopDocs search(ScoreDoc after, Query query, Filter filter, int topN, Sort sort) throws IOException {
        initReader();
        IndexSearcher searcher = new IndexSearcher(reader);
        if(sort == null)
            return searcher.searchAfter(after, query, filter, topN);
        else
            return searcher.searchAfter(after, query, filter, topN, sort);
    }


}
