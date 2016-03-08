package com.shop.service;

import com.shop.dao.PageJdbc;
import com.shop.domain.Goods;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-29.
 * 商品信息业务逻辑接口
 */
public interface GoodsService {

    public String create(Goods goods);

    public void update(Goods goods);

    public Goods getGoodsById(String id);

    /**
     * 按照分类名查看商品
     * @return
     */
    public PageJdbc<Map<String,Object>>getGoodsByCategoryName(String name,int pageIndex,int pageSize);
    /**
     * 按照父节点id查看商品
     * @return
     */
    public PageJdbc<Map<String,Object>>getGoodsByParentId(String parentId,int pageIndex,int pageSize);

    /**
     * 获取推荐商品,用于首页,根据不同分类各推荐指定个数商品
     * @param number 每种分类要推荐的商品个数
     * */
    public Map<String, List<Goods>> getRecommendation(int number);

    /**
     * 更新商品的被浏览次数
     * */
    public void updateScanTime(Goods goods);

    /**
     * 获取相关商品
     * @param text 可以是商品的名称,描述,分类
     * */
    public List<Goods> getRelatedGoods(String text, int topN) throws IOException, ParseException;

    public void removeScanTime(Goods goods);

    public List<Goods> getLatestGoods(int count);

    //根据商品id获取相关商铺和卖家信息
    public Map<String,String> getRelatedInfo(String goodsId);
}
