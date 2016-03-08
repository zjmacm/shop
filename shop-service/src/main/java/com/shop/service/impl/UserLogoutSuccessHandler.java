package com.shop.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销管理的类
 * Created by ldz on 27/11/14.
 */
@Component("userLogoutSuccessHandler")
public class UserLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            // do something
        }

        setDefaultTargetUrl("/index");

        super.onLogoutSuccess(request, response, authentication);
    }
}
