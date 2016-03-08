package com.shop.dao;


import com.shop.domain.Goods;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-27.
 *
 * 商品信息数据库操作接口
 */
public interface GoodsDao {

    public String create(Goods goods);

    public void update(Goods goods);

    public Goods getById(String id);

    /**
     * 根据分类的conds字段分页查询商品
     * @param conds 可以是分类的id，name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageJdbc<Map<String,Object>> pageQueryGoods(String conds,int pageIndex,int pageSize);

    /*获取最新公布的商品信息*/
    public List<Goods> getLatest();

    /**
     *
     * 获取推荐商品信息,返回指定个数的每种分类的推荐商品
     * */
    public List<Goods> getRecommondation(int number);

    /*获取指定分类的商品,分页返回*/
    public List<Goods> getGoodsByCategory(String categoryId, int page);

    /**获取所有商品的数据
     * @param fields 要查询的字段
     * @return map里包含要查询的字段,list是所有的元组
     * */
    public List<Map<String, Object>> getAllGoods(String[] fields);
}
