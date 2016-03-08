package com.shop.service;

import com.shop.dao.PageJdbc;
import com.shop.domain.OfflineOrder;

import java.util.Map;

/**
 * 线下交易的订单
 * Created by ldz on 10/12/14.
 */
public interface OfflineOrderService {

    public String create(OfflineOrder order);

    public void update(OfflineOrder order);

    /**
     * 根据订单编号查看订单
     * @param id
     * @return
     */
    public OfflineOrder getOrderById(String id);

    /**
     * 根据userid分页查看所有订单
     * @param userId
     * @return
     */
    public PageJdbc<Map<String,Object>> getOrdersByUserId(String userId,int pageIndex,int pageSize);

    /**
     * 根据是sellerid分页查看所有订单
     * @param sellerId
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
     * 分页查看卖家成功交易的订单
     * @param sellerId
     * @return
     */
    public  PageJdbc<Map<String,Object>>getSuccOrdersBySellerId(String userId,int pageIndex,int pageSize);

    /**
     * 查看买家当前未完成的线下交易的订单
     * @param userId
     * @return
     */
    public  PageJdbc<Map<String,Object>>getCurrOrdersByUserId(String userId,int pageIndex,int pageSize);

    /**
     * 查看卖家当前未完成订单
     * @param sellerId
     * @return
     */
    public  PageJdbc<Map<String,Object>>getCurrOrdersBySellerId(String sellerId,int pageIndex,int pageSize);


}
