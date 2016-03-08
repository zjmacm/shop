package com.shop.service.impl;

import com.shop.dao.GoodsAnswerDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.Goods;
import com.shop.domain.GoodsAnswer;
import com.shop.service.GoodsAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by ldz on 08/12/14.
 */
@Service("goodsAnserService")
public class GoodsAnswerServiceImpl implements GoodsAnswerService {
    @Autowired
    private GoodsAnswerDao goodsAnswerDao;
    @Override
    public String create(GoodsAnswer answer) {
        Assert.notNull(answer,"answer must be not null");
        Assert.hasText(answer.getQuestionId(),"questionId must be not null");
        Assert.hasText(answer.getAnsComment(),"ansComment must be not null");

        return goodsAnswerDao.create(answer);
    }

    @Override
    public void update(GoodsAnswer answer) {
        Assert.notNull(answer,"answer must be not null");
        Assert.hasText(answer.getId(),"id must be not null");
        goodsAnswerDao.update(answer);
    }

    @Override
    public void remove(GoodsAnswer answer) {
        Assert.notNull(answer,"answer must be not null");
        Assert.hasText(answer.getId(),"id must be not null");
        goodsAnswerDao.remove(answer);
    }

    @Override
    public GoodsAnswer getGoodsAnswerById(String id) {
        Assert.hasText(id,"id not null");

        return goodsAnswerDao.getGoodsAnswerById(id);
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
    public PageJdbc<Map<String, Object>> getAnswersByQuestionId(String questionId, int pageIndex, int pageSize) {
        Assert.hasText(questionId,"questionId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsAnswerDao.getAnswersByQuestionId(questionId,pageIndex,pageSize);
    }

    /**
     * 分页查询一个商品的所有回复
     *
     * @param goodsId
     * @param pageIndex 当前页 比如：第一页（起始页）
     * @param pageSize  每页大小
     * @return
     */
    @Override
    public PageJdbc<Map<String, Object>> getAnswersByGoodsId(String goodsId, int pageIndex, int pageSize) {
        Assert.hasText(goodsId,"goodsId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsAnswerDao.getAnswersByQuestionId(goodsId,pageIndex,pageSize);
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
        Assert.hasText(storeId,"storeId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsAnswerDao.getAnswersByQuestionId(storeId,pageIndex,pageSize);
    }
}
