package com.shop.service;

import com.shop.domain.User;
import com.shop.utils.Tool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by qf on 2014/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/shop-service.xml"})
public class RedisUserServiceTest {
    @Autowired
    RedisUserService redisUserService;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId(Tool.getUUID32());
        user.setUserName("user_name");
        user.setRealName("real_name");
        user.setGender("1");
        user.setMobile("mobile");
        user.setRegisterDate(new Date());
        user.setUserStatus("1");
        user.setBirthday(new Date());
        user.setEmail("email");
        user.setPassword("password");
        user.setIcon("icon");
        redisUserService.createUser(user);
    }

    @Test
    public void testFindUserByEmail(){
        String email = "email";
        System.err.println("====findUserByEmail======"+redisUserService.findUserByEmail(email));
    }

    @Test
    public void testFindUserByUserName(){
        String userName = "user_name";
        System.err.println("=====findUserByUserName====="+redisUserService.findUserByUserName(userName));
    }
}
