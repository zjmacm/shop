package com.shop.dao.test;

import com.shop.dao.GoodsCommentDao;
import com.shop.dao.PageJdbc;
import com.shop.domain.GoodsComment;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 14-12-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class GoodsCommentDaoTest {

    @Autowired
    GoodsCommentDao goodsCommentDao;

    @Test
    public void createTest(){
        GoodsComment comment = new GoodsComment();
        comment.setCommentScore(5);
        comment.setGoodsId("goods1");
        comment.setUserId("user1");
        this.goodsCommentDao.create(comment);
    }

    @Test
    public void updateTest(){
        GoodsComment comment = new GoodsComment();
        comment.setId("ff8081814a2e36eb014a2e36ee340000"); //the ff8081814a2e36eb014a2e36ee340000 is created from createTest() Method
        comment.setCommentScore(4);
        comment.setGoodsId("goods1");
        comment.setUserId("user1");
        this.goodsCommentDao.update(comment);
    }

    @Test
    public void removeTest(){
        GoodsComment goodsComment = new GoodsComment();
        goodsComment.setId("ff8081814a2e36eb014a2e36ee340000"); //the ff8081814a2e36eb014a2e36ee340000 is created from createTest() Method
        goodsComment.setCommentScore(4);
        goodsComment.setGoodsId("goods1");
        goodsComment.setUserId("user1");
        this.goodsCommentDao.remove(goodsComment);
    }

    @Test
    public void pageQueryTest(){
        PageJdbc<Map<String,Object>> comments = this.goodsCommentDao.getCommentsByGoodId("goods1",1,2);
        List<Map<String,Object>> commentList = comments.getList();
        for(Map<String,Object> comment:commentList){
            System.out.println(comment.get("id")+"/"+comment.get("comment_score"));
        }
    }

}
