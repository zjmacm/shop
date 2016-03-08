package com.shop.dao;

import com.shop.domain.OfflineOrder;

import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
public interface OfflineOrderDao {

    public String create(OfflineOrder order);

    public void update(OfflineOrder order);

    /**
     * 根据订单编号查看订单
     * @param id  订单编号
     * @return
     */
    public OfflineOrder getOrderById(String id);

    /**
     * 根据userid分页查看所有买家订单
     * @param userId
     * @return
     */
    public PageJdbc<Map<String,Object>> getOrdersByUserId(String userId,int pageIndex,int pageSize);

    /**
     * 根据sellerid分页查看所有买家订单
     * @param sellerId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageJdbc<Map<String,Object>> getOrdersBySellerId(String sellerId,int pageIndex,int pageSize);


    /**
     * 分页查看买家成功交易的订单
     * @param userId
     * @return
     */
    public  PageJdbc<Map<String,Object>>getSuccOrdersByUserId(String userId,int pageIndex,int pageSize);

    /**
     * 分页查看卖家成功交易订单
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public  PageJdbc<Map<String,Object>>getSuccOrdersBySellerId(String userId,int pageIndex,int pageSize);

    /**
     * 查看当前买家未完成的线下交易的订单
     * @param userId 买家
     * @return
     */
    public  PageJdbc<Map<String,Object>>getCurrOrdersByUserId(String userId,int pageIndex,int pageSize);

    /**
     * 查看当前卖家未完成的线下交易的订单
     * @param sellerId 买家
     * @return
     */
    public  PageJdbc<Map<String,Object>>getCurrOrdersBySellerId(String sellerId,int pageIndex,int pageSize);


}
