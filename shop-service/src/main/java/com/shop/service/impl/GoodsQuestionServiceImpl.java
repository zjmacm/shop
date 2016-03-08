package com.shop.service.impl;

import com.shop.dao.GoodsQuestionDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.GoodsQuestion;
import com.shop.service.GoodsQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ldz on 08/12/14.
 */
@Service("goodsQuestionService")
public class GoodsQuestionServiceImpl implements GoodsQuestionService{
    @Autowired
    private GoodsQuestionDao goodsQuestionDao;


    @Override
    public String create(GoodsQuestion question) {
        Assert.notNull(question, "question must be not null");
        Assert.hasText(question.getGoodsId(),"goodsId must be not null");
        Assert.hasText(question.getAskComment(),"askComment must be not null");
        return goodsQuestionDao.create(question);
    }

    @Override
    public void update(GoodsQuestion question) {
        Assert.notNull(question, "answer must be not null");
        Assert.hasText(question.getGoodsId(),"goodsId must be not null");
        goodsQuestionDao.update(question);
    }

    @Override
    public GoodsQuestion getGoodsQuestionById(String id) {
        Assert.hasText(id,"id not null");

        return goodsQuestionDao.getGoodsQuestionById(id);

    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesByGoodsId(String goodsId, int pageIndex, int pageSize) {
        Assert.hasText(goodsId,"goodsId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsQuestionDao.getQuesByGoodsId(goodsId, pageIndex, pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesByStoreId(String storeId, int pageIndex, int pageSize) {
        Assert.hasText(storeId,"storeId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsQuestionDao.getQuesByStoreId(storeId, pageIndex, pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesNoAnswerByStoreId(String storeId, int pageIndex, int pageSize) {
        Assert.hasText(storeId,"storeId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsQuestionDao.getQuesNoAnswerByStoreId(storeId,pageIndex,pageSize);
    }

    @Override
    public PageJdbc<Map<String, Object>> getQuesNoAnswerByGoodsId(String goodsId, int pageIndex, int pageSize) {
        Assert.hasText(goodsId,"goodsId must be not null");
        Assert.isTrue(pageIndex>0&&pageSize>=0,"IllegalArgumentException");
        return goodsQuestionDao.getQuesNoAnswerByGoodsId(goodsId,pageIndex,pageSize);
    }

    @Override
    public List<GoodsQuestion> getQuesTopNByStoreId(String storeId, int n) {
        Assert.hasText(storeId, "storeId must be not null");
        Assert.isTrue(n>0,"n must be > 0");
        List<Map<String, Object>> tmp = goodsQuestionDao.getQuesTopNByStoreId(storeId,n);
        List<GoodsQuestion> result = new ArrayList<>();
        for(Map<String,Object> question:tmp){
            GoodsQuestion tmpQuestion = new GoodsQuestion();
            if(question.containsKey("id"))
                tmpQuestion.setId((String)question.get("id"));
            if(question.containsKey("goods_id"))
                tmpQuestion.setGoodsId((String) question.get("goods_id"));
            if(question.containsKey("ask_comment"))
                tmpQuestion.setAskComment((String) question.get("ask_comment"));
            if(question.containsKey("user_id"))
                tmpQuestion.setUserId((String) question.get("user_id"));
            if(question.containsKey("question_times"))
                tmpQuestion.setQuestionTimes((Integer) question.get("question_times"));
            if(question.containsKey("status"))
                tmpQuestion.setStatus((String)question.get("status"));
            if(question.containsKey("create_time"))
                tmpQuestion.setCreateTime((Date) question.get("create_time"));
            result.add(tmpQuestion);
        }
        return result;
    }

    @Override
    public List<GoodsQuestion> getQuesTopNByGoodsId(String goodsId, int n) {
        Assert.hasText(goodsId, "storeId must be not null");
        Assert.isTrue(n>0,"n must be > 0");
        List<Map<String, Object>> tmp = goodsQuestionDao.getQuesTopNByGoodsId(goodsId,n);
        List<GoodsQuestion> result = new ArrayList<>();
        for(Map<String,Object> question:tmp){
            GoodsQuestion tmpQuestion = new GoodsQuestion();
            if(question.containsKey("id"))
                tmpQuestion.setId((String)question.get("id"));
            if(question.containsKey("goods_id"))
                tmpQuestion.setGoodsId((String) question.get("goods_id"));
            if(question.containsKey("ask_comment"))
                tmpQuestion.setAskComment((String) question.get("ask_comment"));
            if(question.containsKey("user_id"))
                tmpQuestion.setUserId((String) question.get("user_id"));
            if(question.containsKey("question_times"))
                tmpQuestion.setQuestionTimes((Integer) question.get("question_times"));
            if(question.containsKey("status"))
                tmpQuestion.setStatus((String)question.get("status"));
            if(question.containsKey("create_time"))
                tmpQuestion.setCreateTime((Date) question.get("create_time"));
            result.add(tmpQuestion);
        }
        return result;
    }
}
