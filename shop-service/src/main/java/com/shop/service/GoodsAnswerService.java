package com.shop.service;

import com.shop.dao.PageJdbc;
import com.shop.domain.GoodsAnswer;

import java.util.Map;

/**
 * 询问回复管理接口
 * Created by ldz on 08/12/14.
 */
public interface GoodsAnswerService {

    public String create(GoodsAnswer answer);

    public void update(GoodsAnswer answer);
    public  void remove(GoodsAnswer answer);

    public GoodsAnswer getGoodsAnswerById(String id);

    /**
     * 分页查询一个询问的所有回复
     * @param questionId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>> getAnswersByQuestionId(String questionId,int pageIndex, int pageSize);
    /**
     * 分页查询一个商品的所有回复
     * @param goodsId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>> getAnswersByGoodsId(String goodsId,int pageIndex, int pageSize);

    /**
     * 分页查看店铺所有的回复
     * @param storeId
     * @param  pageIndex 当前页 比如：第一页（起始页）
     * @param  pageSize 每页大小
     * @return
     */
    public PageJdbc<Map<String, Object>>getQuestionsByStoreId(String storeId,int pageIndex, int pageSize);

}
