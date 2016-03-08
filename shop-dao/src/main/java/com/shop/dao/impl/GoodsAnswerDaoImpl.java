package com.shop.dao.impl;

import com.shop.dao.GoodsAnswerDao;
import com.shop.dao.Order;
import com.shop.dao.PageJdbc;
import com.shop.dao.SimpleDaoJDBC;
import com.shop.domain.GoodsAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ldz on 09/12/14.
 */
@Repository("goodsAnswerDao")
public class GoodsAnswerDaoImpl extends HibernateDao implements GoodsAnswerDao {
    @Autowired
    private SimpleDaoJDBC simpleDaoJDBC;


    @Override
    public String create(GoodsAnswer answer) {
        return save(answer);
    }

    @Override
    public void update(GoodsAnswer answer) {
        update(answer);
    }

    @Override
    public void remove(GoodsAnswer answer) {
        remove(answer);
    }

    @Override
    public GoodsAnswer getGoodsAnswerById(String id) {
        return get(GoodsAnswer.class,id);
    }
    /**
     * 分页查询一个询问的所有回复
     *
     * @param questionId
     * @param pageIndex  当前页 比如：第一页（起始页）
     * @param pageSize   每页大小
     * @return
     */
    @Override
    public PageJdbc<Map<String, Object>> getAnswersByQuestionId(String questionId,int pageIndex, int pageSize){
        //为啥要用final
        final String sql = "select * from shop_goods_answer where question_id =:question_id";

        Map<String, Object> conds = new HashMap<>();
        //绑定条件
        conds.put("question_id",questionId);
        //指定排序条件,desc表示降序显示
        Order order =new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }

    /**
     * 分页查询一个商品的所有回复
     * @param goodsId
     * @param pageIndex  当前页 比如：第一页（起始页）
     * @param pageSize   每页大小
     * @return
     */
    @Override
    public PageJdbc<Map<String, Object>> getAnswersByGoodsId(String goodsId, int pageIndex, int pageSize) {
        final String sql = "select * from shop_goods_answer where question_id in " +
                "(select id from shop_goods_question where goods_id =:goods_id)";
        Map<String, Object> conds = new HashMap<>();
        //绑定条件
        conds.put("goods_id",goodsId);
        //指定排序条件
        Order order =new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }

    /**
     * 分页查看店铺所有的回复
     *
     * @param storeId
     * @param pageIndex 当前页 比如：第一页（起始页）
     * @param pageSize  每页大小
     * @return
     */
    @Override
    public PageJdbc<Map<String, Object>> getQuestionsByStoreId(String storeId, int pageIndex, int pageSize) {
        final String sql = "select answer.id ,answer.question_id,answer.ans_comment,answer.create_time " +
                           "from shop_goods_answer answer join shop_goods_question question " +
                           "on answer.question_id = question.id " +
                           "join shop_goods goods on question.goods_id = goods.id where store_id =:store_id ";
        Map<String ,Object> conds = new HashMap<>();
        conds.put("store_id",storeId);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,conds,pageIndex,pageSize,order);
    }
}
