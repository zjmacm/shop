package com.shop.dao.test;

import com.shop.dao.CategoryDao;
import com.shop.domain.Category;
import com.shop.utils.DateTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class CategoryDaoTest {

    @Autowired
    CategoryDao dao;

    @Test
    public void saveTest(){
        Category category = new Category();
        category.setCategory("护肤品");
        category.setCreateTime(new Date(DateTool.getCurrentDate()));

        category.setParentId("生活用品");
        dao.create(category);
    }

    @Test
    public void getTest(){
        Category category = dao.getCategoryByName("电子产品");
        Assert.isTrue(category.getCategory().equals("电子产品"));
    }

    @Test
    public void getTestById(){
        Category category = dao.getCategoryById("ff80808149eb0bca0149eb0bcc020000");
        Assert.isTrue(category.getCategory().equals("手机"));
    }

    @Test
    public void updateTest(){
        Category category = dao.getCategoryById("ff80808149eb0bca0149eb0bcc020000");
        category.setCategory("平板");
        dao.update(category);
        Category c = dao.getCategoryById("ff80808149eb0bca0149eb0bcc020000");
        Assert.isTrue(c.getCategory().equals("平板"));
    }

    @Test
    public void removeTest(){
        Category category = new Category();
        category.setId("ff80808149eb0bca0149eb0bcc020000");
        dao.remove(category);
    }

    @Test
    public void getAllTest(){
        List<Map<String, Object>> list = dao.getAllCategory();
        System.out.println(list);
    }
}
