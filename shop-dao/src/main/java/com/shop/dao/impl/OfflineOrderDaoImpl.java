package com.shop.dao.impl;

import com.shop.dao.OfflineOrderDao;
import com.shop.dao.Order;
import com.shop.dao.PageJdbc;
import com.shop.dao.SimpleDaoJDBC;
import com.shop.domain.OfflineOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
@Repository("offlineOrderDao")
public class OfflineOrderDaoImpl  extends  HibernateDao implements OfflineOrderDao {
    @Autowired
    private SimpleDaoJDBC simpleDaoJDBC;
    @Override
    public String create(OfflineOrder order) {
        return save(order);
    }

    @Override
    public void update(OfflineOrder order) {
        update(order);
    }

    @Override
    public OfflineOrder getOrderById(String id) {
        return get(OfflineOrder.class,id);
    }

    @Override
    public PageJdbc<Map<String, Object>> getOrdersByUserId(String userId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_offline_order where user_id =:user_id";
        Map<String,Object> conds =new HashMap<>();
        conds.put("user_id",userId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }
    @Override
    public PageJdbc<Map<String, Object>> getOrdersBySellerId(String sellerId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_offline_order where seller_id =:seller_id";
        Map<String,Object> conds =new HashMap<>();
        conds.put("seller_id",sellerId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }
    @Override
    public PageJdbc<Map<String, Object>> getSuccOrdersByUserId(String userId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_offline_order where user_id =:user_id and status ='1'";
        Map<String,Object> conds =new HashMap<>();
        conds.put("user_id",userId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);

    }
    @Override
    public PageJdbc<Map<String, Object>> getSuccOrdersBySellerId(String sellerId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_offline_order where seller_id =:seller_id and status ='1'";
        Map<String,Object> conds =new HashMap<>();
        conds.put("seller_id",sellerId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);

    }

    @Override
    public PageJdbc<Map<String, Object>> getCurrOrdersByUserId(String userId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_offline_order where user_id =:user_id and status ='2'";
        Map<String,Object> conds =new HashMap<>();
        conds.put("userId",userId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);

    }

    @Override
    public PageJdbc<Map<String, Object>> getCurrOrdersBySellerId(String sellerId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_offline_order where seller_id =:seller_id and status ='2'";
        Map<String,Object> conds =new HashMap<>();
        conds.put("seller_id",sellerId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);

    }
}
