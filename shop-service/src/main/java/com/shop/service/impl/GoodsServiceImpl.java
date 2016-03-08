package com.shop.service.impl;

import com.shop.dao.GoodsDao;
import com.shop.dao.PageJdbc;
import com.shop.dao.impl.RedisDao;
import com.shop.dao.lucene.SearchEngin;
import com.shop.domain.Goods;
import com.shop.domain.Seller;
import com.shop.service.CategoryService;
import com.shop.service.GoodsService;
import com.shop.service.SellerService;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;

/**
 * Created by yj on 14-11-29.
 */
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService{

    //在redis中有序集合的key前缀,完整的key由hotSet+categoryId组成.分数是商品的浏览次数
    private final String zSetKey = "hotSet";

    //key是子分类的id value是父分类的封装类
    private final Map<String, CategoryServiceImpl.Hierarchy> categories;

    //所有一级分类的信息
    private final List<CategoryServiceImpl.Hierarchy> parents;

    GoodsDao goodsDao;

    RedisDao redisDao;

    CategoryService categoryService;

    SellerService sellerService;
    //SearchEngine engine;
    SearchEngin engin;

    @Autowired
    public GoodsServiceImpl( GoodsDao goodsDao, RedisDao redisDao,
                             CategoryService categoryService, SearchEngin engin,
                             SellerService sellerService) {
        this.goodsDao = goodsDao;
        this.redisDao = redisDao;
        this.categoryService = categoryService;
        this.categories = new HashMap<>();
        this.parents = new ArrayList<>();
        this.engin = engin;
        this.sellerService = sellerService;
        init();
    }

    private void init(){
        Map<String, CategoryServiceImpl.Hierarchy> all = categoryService.getAll();
        Set<String> keySet = all.keySet();
        Iterator<String> key = keySet.iterator();
        //以childId -> parent 的形式建立map
        while(key.hasNext()){
            String parentId = key.next();
            CategoryServiceImpl.Hierarchy parent = all.get(parentId);
            parents.add(parent);//保存一级分类
            List<CategoryServiceImpl.Hierarchy> children = parent.getChildren();
            for(CategoryServiceImpl.Hierarchy child : children){
                categories.put(child.getId(),parent);
            }
        }
    }

    /**
     * 根据子分类id获取父分类id
     * 如果childId本身就是一级分类的id则返回它本身
     * */
    private String getParentId(String childId){
        if(categories.containsKey(childId))
            return childId;
        return categories.get(childId).getId();
    }

    @Override
    public String create(Goods goods) {
        return goodsDao.create(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public Goods getGoodsById(String id) {
        Goods goods = goodsDao.getById(id);
        //更新浏览次数
        goods.setScanTime(goods.getScanTime()+1);
        goodsDao.update(goods);
        return goods;
    }



    @Override
    public PageJdbc<Map<String, Object>> getGoodsByCategoryName(String categoryName, int pageIndex, int pageSize) {
        Assert.hasText(categoryName, "categoryName must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        String category = categoryName;
        return goodsDao.pageQueryGoods(category, pageIndex, pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getGoodsByParentId(String parentId, int pageIndex, int pageSize) {
        Assert.hasText(parentId, "categoryName must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        String parent_id = parentId;   //字段需要对应
        return goodsDao.pageQueryGoods(parent_id, pageIndex, pageSize);
    }

    //从缓存获取所有一级分类下的热门商品
    @Override
    public Map<String, List<Goods>> getRecommendation(int number) {
        Map<String, List<Goods>> result = new HashMap<>();

        for(CategoryServiceImpl.Hierarchy parent : parents){
            String key = zSetKey + "." + parent.getId();
            ArrayList<Goods> hotGoods = new ArrayList<Goods>();
            hotGoods.addAll(redisDao.getRangeZSet(key, 0, number - 1));
            result.put(parent.getId(), hotGoods);
        }
        return result;
    }

    /**
     * 通过缓存维持商品的浏览次数
     * */
    @Override
    public void updateScanTime(Goods goods){
        String key = zSetKey + "." + getParentId(goods.getCategoryId());
        redisDao.incrZSet(key, 1, goods);
    }

    @Override
    public List<Goods> getRelatedGoods(String text, int topN) throws IOException, ParseException {
        TopDocs allInfo = engin.multiFieldQuery(null, text, null, topN, null,
                new String[]{"name", "description", "category"},
                new BooleanClause.Occur[]{BooleanClause.Occur.SHOULD,
                        BooleanClause.Occur.SHOULD,
                        BooleanClause.Occur.SHOULD});
        ScoreDoc[] docs = allInfo.scoreDocs;
        IndexReader reader = engin.getReader();
        List<Goods> result = new ArrayList<>();
        for(ScoreDoc doc : docs){
            Document document = reader.document(doc.doc);
            Goods goods = new Goods();
            goods.setId(document.get("id"));
            goods.setDescription(document.get("description"));
            goods.setName(document.get("name"));
            goods.setPrice(Double.parseDouble(document.get("price")));
            goods.setBrand(document.get("brand"));
            result.add(goods);
        }
        return result;
    }

    /**
     * 从缓存中移除某个商品的浏览次数记录,一般是商品下架时调用
     * */
    public void removeScanTime(Goods goods){
        String key = zSetKey + "." + getParentId(goods.getCategoryId());
        redisDao.removeZSet(key, goods);
    }

    @Override
    public List<Goods> getLatestGoods(int count) {
        List<Goods> result = goodsDao.getLatest();
        return result.subList(0,count-1);
    }

    @Override
    public Map<String, String> getRelatedInfo(String goodsId) {
        Goods goods = getGoodsById(goodsId);
        Seller seller = sellerService.getSeller(goods.getSellerId());
        Map<String, String> result = new HashMap();
        result.put("sellerName", seller.getOwnerName());
        result.put("school", seller.getSchool());
        result.put("college", seller.getDepartment());
        return result;
    }
}
