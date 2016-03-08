package com.shop.service;

import com.shop.domain.User;
import com.shop.utils.Tool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qf on 2014/11/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/shop-service.xml"})
public class RedisServiceTest {
    @Autowired
    RedisService redisService;

    @Test
    public void testGetObjectFromJson(){
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
        redisService.setObjectToJson(user.getUserName(),user);
        System.out.println("get user from Json:"+redisService.getObjectFromJson(user.getEmail(),user.getClass()));
    }

    @Test
    public void testSetAndGetObjectAsJson(){
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
        System.out.println("set object as json:"+redisService.setObject2Json(user.getEmail(),user));
        System.out.println("get object as json:"+redisService.getObjectAsJson(user.getUserName()));
    }

    @Test
    public void testGetFromList(){
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

        List list=new ArrayList();
        redisService.setToList("list",user,1);
        System.out.println("get object from list:" + redisService.getFromList("list", 0));
        User user2=new User();
        user2.setId(Tool.getUUID32());
        user2.setUserName("user_name2");
        user2.setRealName("real_name2");
        redisService.setToList("list",user2,1);
        System.out.println("get object2 from list:"+redisService.getFromList("list",0));

    }

}
