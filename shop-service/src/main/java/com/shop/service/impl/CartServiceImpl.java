package com.shop.service.impl;

import com.shop.dao.CartDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.Cart;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by ldz on 09/12/14.
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired//注入
    private CartDao cartDao;
    @Override
    public String create(Cart cart) {
        Assert.notNull(cart,"cart not null");
        Assert.hasText(cart.getId(),"id not null");
        Assert.hasText(cart.getGoodsId(),"goodsId not null");
        return cartDao.create(cart);
    }

    @Override
    public void update(Cart cart) {
        Assert.notNull(cart,"cart not null");
        Assert.hasText(cart.getId(),"id not null");
        cartDao.update(cart);
    }

    @Override
    public void remove(Cart cart) {
        Assert.notNull(cart,"cart not null");
        Assert.hasText(cart.getId(),"id not null");
        cartDao.remove(cart);
    }

    @Override
    public void removeAllByUserId(String userId) {
        Assert.hasText(userId,"userId not null");
        cartDao.removeAllByUserId(userId);
    }

    @Override
    public void removeCartsByIds(String[] ids) {
        Assert.notNull(ids,"ids not null");
        cartDao.removeCartsByIds(ids);
    }

    @Override
    public PageJdbc<Map<String, Object>> getCartByUserId(String userId,int pageIndex,int pageSize) {
        Assert.hasText(userId,"userId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return cartDao.getCartByUserId(userId,pageIndex,pageSize);
    }
}
