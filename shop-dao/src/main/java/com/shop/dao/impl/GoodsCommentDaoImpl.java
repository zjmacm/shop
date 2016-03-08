package com.shop.dao.impl;

import com.shop.dao.GoodsCommentDao;
import com.shop.dao.Order;
import com.shop.dao.PageJdbc;
import com.shop.dao.SimpleDaoJDBC;
import com.shop.domain.GoodsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 14-12-9.
 */
@Repository("goodsCommentDao")
public class GoodsCommentDaoImpl extends HibernateDao implements GoodsCommentDao{

    @Autowired
    SimpleDaoJDBC simpleDaoJDBC;

//    @Override
//    public PageJdbc<GoodsComment> getCommentsByGoodId(String goodsId, int pageIndex, int pageSize) {
//        String sql = "select * from shop_goods_comment where goods_id=:goods_id";
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("goods_id",goodsId);
//        Order order = new Order().desc("create_time");
//        PageJdbc<Map<String,Object> > map2 = this.simpleDaoJDBC.pageQuery(sql,map,pageIndex,pageSize,order);
//        return this.simpleDaoJDBC.pageQuery(GoodsComment.class,sql,map,pageIndex,pageSize,order);
//    }

    @Override
    public PageJdbc<Map<String,Object> > getCommentsByGoodId(String goodsId, int pageIndex, int pageSize) {
        String sql = "select * from shop_goods_comment where goods_id=:goods_id";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goods_id",goodsId);
        Order order = new Order().desc("create_time");
        return this.simpleDaoJDBC.pageQuery(sql,map,pageIndex,pageSize,order);
    }

    @Override
    public String create(GoodsComment goodsComment) {
        return super.save(goodsComment);
    }

    @Override
    public void update(GoodsComment newGoodComment) {
        super.update(newGoodComment);
    }

    @Override
    public void removeByUUID(String uuid) {
//        GoodsComment comment = this.simpleDaoJDBC.query
    }

    @Override
    public void remove(GoodsComment goodsComment) {
        super.remove(goodsComment);
    }
}
