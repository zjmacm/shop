package com.shop.service.impl;

import com.shop.dao.FavoriteDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.Favorite;
import com.shop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by ldz on 09/12/14.
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired
    private FavoriteDao favoriteDao;
    @Override
    public String create(Favorite favorite) {

        Assert.notNull(favorite, "cart not null");
        Assert.hasText(favorite.getId(),"id not null");
        Assert.hasText(favorite.getGoodsId(),"goodsId not null");
        return favoriteDao.create(favorite);
    }

    @Override
    public void update(Favorite favorite) {
        Assert.notNull(favorite, "cart not null");
        Assert.hasText(favorite.getId(),"id not null");
        favoriteDao.update(favorite);
    }

    @Override
    public void remove(Favorite favorite) {
        Assert.notNull(favorite, "cart not null");
        Assert.hasText(favorite.getId(),"id not null");
        favoriteDao.remove(favorite);
    }

    @Override
    public void removeAllByUserId(String userId) {
        Assert.hasText(userId,"userId not null");
        favoriteDao.removeAllByUserId(userId);
    }

    @Override
    public void removeFavoritesByIds(String[] ids) {
        Assert.notNull(ids,"ids not null");
        favoriteDao.removeFavoritesByIds(ids);
    }

    @Override
    public PageJdbc<Map<String, Object>> getFavoriteByUserId(String userId,int pageIndex,int pageSize) {
        Assert.hasText(userId,"userId not null");
        Assert.isTrue((pageIndex>0&&pageSize>=0),"pageSize and pageIndex illegal argument");
        return favoriteDao.getFavoriteByUserId(userId,pageIndex,pageSize);
    }
}
