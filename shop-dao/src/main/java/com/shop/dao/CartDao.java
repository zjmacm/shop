package com.shop.dao;

import com.shop.domain.Cart;

import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
public interface CartDao {

    public String create(Cart cart);

    public void update(Cart cart);

    public void remove(Cart cart);

    /**
     * 移除所有购物车里面商品
     * @param userId
     */
    public void removeAllByUserId(String userId);

    /**
     * 批量移除购物车里面商品
     * @param ids
     */
    public int[] removeCartsByIds(String []ids);

    /**
     * 分页用户查询购物车里面的商品
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageJdbc<Map<String,Object>>getCartByUserId(String userId,int pageIndex,int pageSize);

}
