package com.shop.dao.impl;

import com.shop.dao.CartDao;
import com.shop.dao.Order;
import com.shop.dao.PageJdbc;
import com.shop.dao.SimpleDaoJDBC;
import com.shop.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
@Repository("cartDao")
public class CartDaoImpl extends  HibernateDao implements CartDao {
    @Autowired
    private SimpleDaoJDBC simpleDaoJDBC;

    @Override
    public String create(Cart cart) {
        return save(cart);
    }

    @Override
    public void update(Cart cart) {
        update(cart);
    }

    @Override
    public void remove(Cart cart) {
        remove(cart);
    }

    @Override
    public void removeAllByUserId(String userId) {
        final String sql = "delete from shop_cart where user_id =:user_id";
        Map<String,Object>conds = new HashMap<>();
        conds.put("user_id",userId);
        simpleDaoJDBC.executeUpdate(sql,conds);
    }


    /**
     * 需要测试
     * @param ids
     * @return
     */
    @Override
    public int[] removeCartsByIds(String[] ids) {
        final String sql = "delete from shop_cart where id =:id";
        List<Map<String ,?>> conds = new ArrayList<>();
        for(String id:ids){
            Map<String ,Object> tmp = new HashMap<>();
            tmp.put("id",id);
            conds.add(tmp);
        }

       return  simpleDaoJDBC.batchUpdate(sql,conds);

    }

    @Override
    public PageJdbc<Map<String, Object>> getCartByUserId(String userId,int pageIndex,int pageSize) {
        final String sql = "select * from shop_cart where user_id:user_id";
        Map<String,Object> conds = new HashMap<>();
        conds.put("user_id",userId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }
}
