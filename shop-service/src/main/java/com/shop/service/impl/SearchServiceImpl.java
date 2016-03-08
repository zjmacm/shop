package com.shop.service.impl;

import com.shop.dao.Page;
import com.shop.dao.impl.RedisDao;
import com.shop.dao.lucene.SearchEngin;
import com.shop.dao.lucene.SerialScoreDoc;
import com.shop.domain.Goods;
import com.shop.service.Constants;
import com.shop.service.SearchService;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 14-12-11.
 *
 * 搜索功能业务逻辑实现类
 */
@Service("SearchService")
public class SearchServiceImpl implements SearchService{

    @Autowired
    SearchEngin engin;

    @Autowired
    RedisDao redisDao;

    //使用时在后面添加搜索条件 作为一次搜索的key
    private final String keyForPage = "search.page.";

    private final String[] fields = {"name", "description", "category"};

    private final BooleanClause.Occur[] conditions = {BooleanClause.Occur.SHOULD,
            BooleanClause.Occur.SHOULD,
            BooleanClause.Occur.SHOULD};

    //缓存失效时间
    private final long expire_time = 5l;
    /**
     * 使用多域模糊搜索,搜索域是 商品名 商品描述 分类
     * page 从1开始
     *
     * 大致过程是 第一次
     * */
    @Override
    public Page simpleGoodsSearch(String text, int page) throws IOException, ParseException {
        if(page < 1)
            throw new IllegalArgumentException("page number must start from 1");
        //搜索第一页时 缓存搜索分页 先搜索出全部结果
        String redisKey = keyForPage + text;
        ScoreDoc[] pageDoc = null;//本页信息
        long lenth = 0;
        if(page == 1) {
            //没有缓存 初始化分页
                TopDocs allInfo = engin.multiFieldQuery(null, text, null,
                        Constants.DEFUAT_SEARCH_CONUT, null,
                        fields, conditions);
                ScoreDoc[] docs = allInfo.scoreDocs;
                lenth = docs.length;
                List pages = new ArrayList();
                //缓存每一页的页末,分页的时候从页末的下一项数据开始取
                for (int i = Constants.SEARCH_PAGE_SIZE - 1; i < lenth; ) {
                    //缓存的实际是包装类
                    pages.add(new SerialScoreDoc().setScoreDoc(docs[i]));
                    i += Constants.SEARCH_PAGE_SIZE;
                }

                redisDao.listAddAll(redisKey, pages, expire_time, TimeUnit.MINUTES);
                //第一页
                int docNumber = Math.min(docs.length, Constants.SEARCH_PAGE_SIZE);
                pageDoc = Arrays.copyOf(docs, docNumber);
        }else {
            //拿到应该访问的页首
            SerialScoreDoc startDoc = (SerialScoreDoc) redisDao.listIndex(redisKey, page - 2,
                    expire_time, TimeUnit.MINUTES);//list索引从0开始,从缓存取数据时,更新失效时间
            //从页首开始取出一页的数据
            TopDocs resultInfo = engin.multiFieldQuery(startDoc.getScoreDoc(), text, null,
                    Constants.DEFUAT_SEARCH_CONUT, null,
                    fields, conditions);
            pageDoc = resultInfo.scoreDocs;
        }
            List<Goods> goodsList = new ArrayList<>();
            for (ScoreDoc doc : pageDoc) {
                Document document = engin.getReader().document(doc.doc);
                Goods goods = new Goods();
                goods.setId(document.get("id"));
                goods.setPrice(Double.parseDouble(document.get("price")));
                goods.setDescription(document.get("description"));
                goods.setName(document.get("name"));
                goods.setBrand(document.get("brand"));
                goodsList.add(goods);
            }
            Page result = new Page((page - 1) * Constants.SEARCH_PAGE_SIZE, lenth,
                    Constants.SEARCH_PAGE_SIZE, goodsList);
            return result;

    }


}
