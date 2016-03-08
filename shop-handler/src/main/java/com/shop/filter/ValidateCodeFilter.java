package com.shop.filter;

import com.shop.captcha.CaptchaServiceSingleton;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.WebAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ldz on 01/12/14.
 */
public class ValidateCodeFilter implements Filter {
    // 是否开启验证码功能
    private boolean openValidateCode = true;
    // 验证码字段
    private String validateCodeParameter = "imageValidate";
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request1, ServletResponse response1,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) request1;
        HttpServletResponse response = (HttpServletResponse) response1;


        String servletPath = request.getServletPath();
        String filterProcessesUrl = "/login_check";
        //获取当前用户的session ID
        String sessionId = request.getSession().getId();
        //获取用户提交的验证码
        String jcaptchaCode = obtainValidateCodeParameter(request);
        if (StringUtils.startsWith(servletPath, filterProcessesUrl)) {
            boolean flag= CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, jcaptchaCode);

            if (flag) {
                chain.doFilter(request, response);
            } else {
                request.getSession().setAttribute(
                        WebAttributes.AUTHENTICATION_EXCEPTION,
                        new Exception("验证码输入不正确！"));
                response.sendRedirect("/login");
            }
        } else {
            try {
                //to-do
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(getValidateCodeParameter());
        return null == obj ? "" : obj.toString().trim();
    }
    public String getValidateCodeParameter() {
        return validateCodeParameter;
    }
}