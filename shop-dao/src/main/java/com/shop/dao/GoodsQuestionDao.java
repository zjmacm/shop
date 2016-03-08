package com.shop.dao;

import com.shop.domain.GoodsQuestion;

import java.util.List;
import java.util.Map;

/**
 * Created by ldz on 09/12/14.
 */
public interface GoodsQuestionDao {


    public String create(GoodsQuestion question);

    public void update(GoodsQuestion question);
    public void remove(GoodsQuestion question);

    public GoodsQuestion getGoodsQuestionById(String id);

    /**
     * 分页查询商品的所有询问
     * @param goodsId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>>getQuesByGoodsId(String goodsId,int pageIndex, int pageSize);

    /**
     * 分页查看店铺所有的询问
     * @param storeId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>>getQuesByStoreId(String storeId,int pageIndex, int pageSize);

    /**
     * 分页查看店铺没有回复的所有问题
     * @param storeId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>>getQuesNoAnswerByStoreId(String storeId,int pageIndex, int pageSize);

    /**
     * 分页查看商品没有回复的询问
     * @param goodsId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>>getQuesNoAnswerByGoodsId(String goodsId,int pageIndex, int pageSize);


    /**
     * 查看店铺询问人数最多的N个问题
     * @param storeId
     * @param n
     * @return
     */
    public List<Map<String,Object>> getQuesTopNByStoreId(String storeId,int n);

    /**
     * 查看商品询问人数最多的N个问题
     * @param goodsId
     * @param n
     * @return
     */
    public  List<Map<String,Object>> getQuesTopNByGoodsId(String goodsId,int n);

}
