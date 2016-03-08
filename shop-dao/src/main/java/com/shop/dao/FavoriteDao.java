package com.shop.dao;

import com.shop.domain.Favorite;

import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
public interface FavoriteDao  {
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
    public int[] removeFavoritesByIds(String []ids);

    /**
     * 分页用户查询收藏夹里面的商品
     * @param userId
     * @return
     */
    public PageJdbc<Map<String,Object>> getFavoriteByUserId(String userId,int pageIndex,int pageSize);


}
