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
 * Created by ldz on 24/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/shop-service.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    public String testCreateUser() throws Exception {
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
        String id = userService.createUser(user);
        System.err.println(id);
        return id;
    }
    @Test
    public void getUser() throws Exception {
        String id = testCreateUser();
        System.err.println("=====getUserById====="+userService.getUser(id));
    }

    @Test
     public void findUserByEmail(){
        String email = "email";
        System.err.println("====findUserByEmail======"+userService.findUserByEmail(email));
    }

    @Test
    public void findUserByUserName(){
        String userName = "user_name";
        System.err.println("=====findUserByUserName====="+userService.findUserByUserName(userName));
    }

}
