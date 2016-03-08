package com.shop.dao.test;

import com.shop.dao.impl.HibernateDao;
import com.shop.dao.impl.JdbcUserDaoImpl;
import com.shop.domain.Authority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by root on 14-11-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class UserDaoTest {

    @Autowired
    JdbcUserDaoImpl dao;
    @Autowired
    HibernateDao hibernateDao;

   /* @Test
    public void createTest(){
        User user = new User();
        user.setUserName("sk");
        user.setPassword("afsdfadsfadf");
        user.setEmail("383548557@qq.com");
        user.setRealName("d");
        dao.createUser(user);
    }*/
    @Test
    public void createAuthoritiesTest(){
        String userId="3e17ef31b0c3466fa10a763caf8e4ff1";
        final String hql = "from Authority authority where authority.userId=?";
        List<Authority> authoritiesList= hibernateDao.find(hql,userId);
        System.err.println(authoritiesList);
    }

}