package com.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yj on 14-11-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/shop-service.xml"})
public class CategoryServiceTest {

    @Autowired
    CategoryService service;

    @Test
    public void removeTest(){

        service.removeByName("书籍");
    }

    @Test
    public void getAllTest(){
        System.out.println(service.getAll());
    }
}
