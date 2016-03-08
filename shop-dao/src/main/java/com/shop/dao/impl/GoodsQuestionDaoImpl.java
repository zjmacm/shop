package com.shop.dao.impl;

import com.shop.dao.GoodsQuestionDao;
import com.shop.dao.Order;
import com.shop.dao.PageJdbc;
import com.shop.dao.SimpleDaoJDBC;
import com.shop.domain.GoodsQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldz on 09/12/14.
 */
@Repository("goodsQuestionDao")
public class GoodsQuestionDaoImpl extends HibernateDao implements GoodsQuestionDao {
    @Autowired
    private SimpleDaoJDBC simpleDaoJDBC;
    @Override
    public String create(GoodsQuestion question) {
        return save(question);
    }

    @Override
    public void update(GoodsQuestion question) {
        update(question);
    }

    @Override
    public void remove(GoodsQuestion question) {
        remove(question);
    }

    @Override
    public GoodsQuestion getGoodsQuestionById(String id) {
        return get(GoodsQuestion.class,id);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesByGoodsId(String goodsId,int pageIndex, int pageSize) {
        final String sql = "select * from shop_goods_question where goods_id =:goods_id";
        Map<String, Object> conds = new HashMap<>();
        //绑定条件
        conds.put("goods_id",goodsId);
        //指定排序条件
        Order order =new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesByStoreId(String storeId,int pageIndex, int pageSize) {
        final String sql = "select * from shop_goods_question where goods_id in (select id as goods_id from shop_goods where store_id=:store_id)";
        Map<String, Object> conds = new HashMap<>();
        //绑定条件
        conds.put("store_id",storeId);
        //指定排序条件
        Order order =new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesNoAnswerByStoreId(String storeId,int pageIndex, int pageSize) {
        //status 0，等待卖家审核，1审核不通过，2审核通过，3已经回复，4.未回复
        final String sql = "select * from shop_goods_question where status='4' and goods_id in (select id as goods_id from shop_goods where store_id=:store_id)";
        Map<String, Object> conds = new HashMap<>();
        //绑定条件
        conds.put("store_id",storeId);
        //指定排序条件
        Order order =new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesNoAnswerByGoodsId(String goodsId,int pageIndex, int pageSize) {
        final String sql = "select * from shop_goods_question where status='4' and goods_id =goods_id";
        Map<String, Object> conds = new HashMap<>();
        //绑定条件
        conds.put("goods_id",goodsId);
        //指定排序条件
        Order order =new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }

    @Override
    public List<Map<String ,Object>> getQuesTopNByStoreId(String storeId, int n) {
        final String sql = "select * from shop_goods_question where goods_id in (select id as goods_id from shop_goods where store_id=:store_id) ";
        //绑定条件
        Map<String, Object> conds = new HashMap<>();

        conds.put("store_id",storeId);

        //指定排序条件为“询问次数”
        Order order =new Order().desc("question_times");
        final int pageIndex = 1;
        final int pageSize = n;
        PageJdbc<Map<String, Object>> tmp = simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);

        return tmp.getList();
    }

    @Override
    public List<Map<String ,Object>> getQuesTopNByGoodsId(String goodsId, int n) {
        final String sql = "select * from shop_goods_question where goods_id = goods_id";
        //绑定条件
        Map<String, Object> conds = new HashMap<>();

        conds.put("goods_id",goodsId);

        //指定排序条件为“询问次数”
        Order order =new Order().desc("question_times");
        final int pageIndex = 1;
        final int pageSize = n;
        PageJdbc<Map<String, Object>> tmp = simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);

        return tmp.getList();
    }
}
