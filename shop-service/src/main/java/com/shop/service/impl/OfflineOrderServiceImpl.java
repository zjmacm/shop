package com.shop.service.impl;

import com.shop.dao.OfflineOrderDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.OfflineOrder;
import com.shop.service.OfflineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
@Service("offlienService")
public class OfflineOrderServiceImpl implements OfflineOrderService {
    @Autowired
    private OfflineOrderDao offlineOrderDao;
    @Override
    public String create(OfflineOrder order) {
        Assert.notNull(order,"acticle not null");
        Assert.hasText(order.getSellerMobile(),"卖家电话不能是空");
        return offlineOrderDao.create(order);
    }

    @Override
    public void update(OfflineOrder order) {
        Assert.notNull(order,"acticle not null");
        Assert.notNull(order.getId(),"order id not null");
        Assert.hasText(order.getSellerMobile(),"卖家电话不能是空");
        offlineOrderDao.update(order);
    }

    @Override
    public OfflineOrder getOrderById(String id) {
        Assert.notNull(id,"id not null");
        return offlineOrderDao.getOrderById(id);
    }

    @Override//重写
    public PageJdbc<Map<String, Object>> getOrdersByUserId(String userId, int pageIndex, int pageSize) {
        Assert.hasText(userId,"userId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return offlineOrderDao.getOrdersByUserId(userId,pageIndex,pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getOrdersBySellerId(String sellerId, int pageIndex, int pageSize) {
        Assert.hasText(sellerId,"sellerId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return offlineOrderDao.getOrdersBySellerId(sellerId,pageIndex,pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getSuccOrdersByUserId(String userId, int pageIndex, int pageSize) {
        Assert.hasText(userId,"userId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return offlineOrderDao.getSuccOrdersByUserId(userId,pageIndex,pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getSuccOrdersBySellerId(String sellerId, int pageIndex, int pageSize) {
        Assert.hasText(sellerId,"sellerId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return offlineOrderDao.getSuccOrdersBySellerId(sellerId, pageIndex, pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getCurrOrdersByUserId(String userId, int pageIndex, int pageSize) {
        Assert.hasText(userId,"userId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return offlineOrderDao.getCurrOrdersByUserId(userId, pageIndex, pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getCurrOrdersBySellerId(String sellerId, int pageIndex, int pageSize) {
        Assert.hasText(sellerId,"sellerId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return offlineOrderDao.getCurrOrdersBySellerId(sellerId, pageIndex, pageSize);
    }
}
