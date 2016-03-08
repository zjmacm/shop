package com.shop.service.impl;

import com.shop.dao.GoodsCommentDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.GoodsComment;
import com.shop.service.GoodsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 14-12-9.
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl implements GoodsCommentService{

    @Autowired
    GoodsCommentDao goodsCommentDao;

    @Override

    public PageJdbc<Map<String, Object>> getCommentsByGoodId(String goodId, int pageIndex, int pageSize) {

        if(goodId==null){
            throw new IllegalArgumentException("the goodId cann't be null!");
        }else if(pageIndex<0){
            throw new IllegalArgumentException("the pageIndex must >=0");
        }else if(pageSize<=0){
            throw new IllegalArgumentException("the pageSize muse >0");
        }else {
            return this.goodsCommentDao.getCommentsByGoodId(goodId, pageIndex, pageSize);
        }
    }

    @Override
    public String create(GoodsComment goodsComment) {
        if(checkComment(goodsComment)){
            return this.goodsCommentDao.create(goodsComment);
        }else{
            throw new IllegalArgumentException("the goodsComment argument not illegal");
        }
    }


    @Override
    public void update(GoodsComment newGoodComment) {
        if(checkComment(newGoodComment)){
            this.goodsCommentDao.update(newGoodComment);
        }else{
            throw new IllegalArgumentException("the newGoodsComment argumemt not illegal");
        }
    }

    @Override
    public void removeByUUID(String uuid) {
        if(uuid==null){
            throw new IllegalArgumentException("the id of comment cann't be null");
        }else{
            this.goodsCommentDao.removeByUUID(uuid);
        }
    }

    @Override
    public void remove(GoodsComment goodsComment) {
        if(checkComment(goodsComment)){
            this.goodsCommentDao.remove(goodsComment);
        }else{
            throw new IllegalArgumentException("the goodsComment arguement not illegal");
        }
    }

    //invalide the goodsComment is illegal
    private boolean checkComment(GoodsComment goodsComment) {
       return goodsComment!=null && goodsComment.getId()!=null && goodsComment.getUserId()!=null && goodsComment.getGoodsId()!=null
               && goodsComment.getCommentScore().intValue()>=1 && goodsComment.getCommentScore().intValue()<=5;
    }
}
