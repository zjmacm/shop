package com.shop.service;

import com.shop.dao.PageJdbc;
import com.shop.domain.Favorite;

import java.util.Map;

/**
 * 收藏夹
 * Created by ldz on 09/12/14.
 */
public interface FavoriteService {

    public String create(Favorite favorite);

    public void update(Favorite favorite);

    public void remove(Favorite favorite);
    /**
     * 移除所有购物车里面商品
     * @param userId
     */
    public void removeAllByUserId(String userId);

    /**
     * 批量移除收藏夹里面商品
     * @param ids
     */
    public void removeFavoritesByIds(String []ids);

    /**
     * 分页用户查询收藏夹里面的商品
     * @param userId
     * @return
     */
    public PageJdbc<Map<String,Object>> getFavoriteByUserId(String userId,int pageIndex,int pageSize);
}
