package com.shop.dao.impl;

import com.shop.dao.FavoriteDao;
import com.shop.dao.Order;
import com.shop.dao.PageJdbc;
import com.shop.dao.SimpleDaoJDBC;
import com.shop.domain.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldz on 10/12/14.
 */
@Repository("FavoriteDao")
public class FavoriteDaoImpl extends HibernateDao implements FavoriteDao{
    @Autowired
    private SimpleDaoJDBC simpleDaoJDBC;
    @Override
    public String create(Favorite favorite) {
        return save(favorite);
    }

    @Override
    public void update(Favorite favorite) {
        update(favorite);
    }

    @Override
    public void remove(Favorite favorite) {
        remove(favorite);
    }

    @Override
    public void removeAllByUserId(String userId) {
        final String sql = "delete from shop_favorite where user_id =:user_id";
        Map<String,Object>conds = new HashMap<>();
        conds.put("user_id",userId);
        simpleDaoJDBC.executeUpdate(sql,conds);
    }

    @Override
    public int[] removeFavoritesByIds(String[] ids) {
        final String sql = "delete from shop_favorite where id =:id";
        List<Map<String ,?>> conds = new ArrayList<>();
        for(String id:ids){
            Map<String ,Object> tmp = new HashMap<>();
            tmp.put("id",id);
            conds.add(tmp);
        }

        return  simpleDaoJDBC.batchUpdate(sql,conds);

    }

    @Override
    public PageJdbc<Map<String, Object>> getFavoriteByUserId(String userId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_favorite where user_id:user_id";
        Map<String,Object> conds = new HashMap<>();
        conds.put("user_id",userId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }
}
