package com.shop.filter;

import com.shop.captcha.CaptchaServiceSingleton;
import com.shop.captcha.ValidateCodeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ldz on 29/11/14.
 */
public class ShopUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // 是否开启验证码功能
    private boolean openValidateCode = false;
    // 验证码字段
    private String validateCodeParameter = "imageValidate";

    boolean flag = false;

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // 只接受POST方式传递的数据
        if (!"POST".equals(request.getMethod()))
            try {
                throw new ValidateCodeException("不支持非POST方式的请求!");
            } catch (ValidateCodeException e) {
                e.printStackTrace();
            }

        // 开启验证码功能的情况
        if (isOpenValidateCode())
            checkValidateCode(request);
        // 获取Username和Password
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // UsernamePasswordAuthenticationToken实现Authentication校验
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // 允许子类设置详细属性
        setDetails(request, authRequest);

        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    public boolean isOpenValidateCode() {
        return openValidateCode;
    }
    public void setOpenValidateCode(boolean openValidateCode) {
        this.openValidateCode = openValidateCode;
    }
    public void checkValidateCode (HttpServletRequest request) throws AuthenticationException{
        //获取当前用户的session ID
        String sessionId = request.getSession().getId();
        //获取用户提交的验证码
        String jcaptchaCode = obtainValidateCodeParameter(request);
        if (null == jcaptchaCode)
            try {
                throw new ValidateCodeException("验证码超时,请重新获取!");
            } catch (ValidateCodeException e) {
                e.printStackTrace();
            }


        //进行验证
        flag= CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, jcaptchaCode);

        if (!flag)
            try {
                throw new ValidateCodeException("验证码不正确,请重新输入!");
            } catch (ValidateCodeException e) {
                e.printStackTrace();
            }
    }
    public String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(getValidateCodeParameter());
        return null == obj ? "" : obj.toString().trim();
    }
    public String getValidateCodeParameter() {
        return validateCodeParameter;
    }
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(getUsernameParameter());
        return null == obj ? "" : obj.toString().trim();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(getPasswordParameter());
        return null == obj ? "" : obj.toString().trim();
    }
}