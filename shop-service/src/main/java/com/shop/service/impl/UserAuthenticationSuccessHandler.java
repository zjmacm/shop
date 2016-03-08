package com.shop.service.impl;

import com.shop.dao.impl.RedisDao;
import com.shop.domain.User;
import com.shop.service.RedisService;
import com.shop.service.UserContext;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登陆成功之后处理的类
 * Created by ldz on 27/11/14.
 */
@Component("userAuthenticationHandler")
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserContext userContext;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {



        super.onAuthenticationSuccess(request, response, authentication);
        //操作redis
        System.out.println("//操作redis");

        //String userName=request.getParameter("usename");
        User user=userContext.getCurrentUser();
        String userName=user.getUserName();
        System.err.println("username"+userName);
        System.err.println("user"+user);
        //redisService.setObject2Json(userName,user);

        //System.out.println("test set user to redis cache:"+redisService.getObjectFromJson(userName,user.getClass()));
        System.err.println("test loginSuccess++++++++++++++++++");
     /*   redisService.setObject2Json(userName,user);

        System.out.println("test set user to redis cache:"+redisService.getObjectFromJson(userName,user.getClass()));
        System.err.println("test loginSuccess++++++++++++++++++");*/
        setDefaultTargetUrl("/");


    }

}
