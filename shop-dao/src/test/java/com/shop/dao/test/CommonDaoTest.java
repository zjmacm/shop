package com.shop.dao.test;

import com.shop.dao.CommonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by yj on 14-12-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class CommonDaoTest {

    @Autowired
    CommonDao dao;

    @Test
    public void searchTest(){
        String text = " 魅族 ";
        System.out.println(dao.searchByName(text));
    }

    @Test
    public void searchFilterTest(){
        String text = " 魅族 ";
        try {
            System.out.println(dao.filterByCategory("手机",dao.searchByName("魅族")));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
