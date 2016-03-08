package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.domain.User;
import com.shop.email.EmailSender;
import com.shop.service.UserService;
import com.shop.uploadImage.Request;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by root on 14-11-25.
 */

@RestController
@Controller("userController")
@RequestMapping("/user")
public class UserController {

    public static final String HOST_NAME = "http://localhost:8088";

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    private EmailSender emailSender;

    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
//        if(user == null){
//            return modelAndView;
//        }
//
//        userService.register(user);
        modelAndView.setViewName("register");
        return modelAndView;

    }

    //注册全部信息验证
    @RequestMapping(value = "/register/register",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> doRegister(@Valid User user,BindingResult result,boolean flag){
        Map<String ,Object> map = getResponseMap();

        if(result!=null && result.hasErrors()) {
            map.put("success",false);
            map.put("error", toJSON(result.getFieldErrors()));
            return map;
        }
        boolean insertSuccess = userService.register(user,flag);
        if(!insertSuccess){
            map.put("success",false);
            map.put("error","用户名已存在！");
        }
        //注册成功，发送邮箱
        sendActiveEmail(user);
        return map;
    }

    //注册激活
    @RequestMapping(value = "/register/active",method = RequestMethod.GET)
    public  ModelAndView doActive(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        final String userName = (String) params.get("userName");
        if(userName==null){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        boolean activeSuccess = userService.active(userName);
        if(activeSuccess){
            modelAndView.setViewName("active");
        }else{
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    //验证用户名是否合法
    @RequestMapping(value="/validate/username",method =  RequestMethod.POST)
    public @ResponseBody Map<String,Object> doValidateUserName(String userName){
        Map<String,Object> map = getResponseMap();
        final String _name = userName==null?"":userName.trim();
        int length = _name.length();
        if(length<2 || length>14){
            map.put("success", false);
            map.put("error", "用户名长度只能在2-14之间");
            return map;
        }
        if(!Pattern.matches("[A-Z a-z][A-Z a-z 0-9 _]*",_name)){
            map.put("success",false);
            map.put("error","用户名格式错误");
        }
        return map;
    }



    @RequestMapping(value="/validate/email",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> doValidateEmail(String email){
        Map<String,Object> map = getResponseMap();
        final String _email = email==null?"":email.trim();
        boolean p = Pattern.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", _email);
        if(!p){
            map.put("success",false);
            map.put("error","邮箱格式错误");
        }
        return map;
    }

    @RequestMapping(value="/validate/password",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> doValidatePassword(String password){
        Map<String,Object> map = getResponseMap();
        System.out.println("password:"+password);
        final String _password = password==null?"":password.trim();
        int length = _password.length();
        if(length<6 || length>20){
            map.put("success",false);
            map.put("error", "密码长度只能在6-20之间");
        }
        return map;
    }

    //生成一个格式化的map
    private Map<String,Object> getResponseMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        map.put("error","");
        return map;
    }


    /**
     * 将错误信息以json格式返回
     * @param fieldErrors 错误的FieldError列表
     * @return json形式的错误信息，键值同表单中的名字
     */
    private String toJSON(List<FieldError> fieldErrors){
        JSONObject jsonObject = new JSONObject();
        for(FieldError error:fieldErrors){
            jsonObject.put(error.getField(),error.getDefaultMessage());
        }
        return jsonObject.toString();
    }

    /**
     *发送激活邮件
     * @param user 接收邮件的用户
     */
    private void sendActiveEmail(User user){
        Map<String,Object> context= new HashMap<String,Object>();
        final String activeCode = UUID.randomUUID().toString(); //the random actice code
        final String userName = user.getUserName();
        final String activeUrl = genereateUrlByGet(userName,activeCode);
        context.put("message","donahue");
        context.put("userName",user.getUserName());
        context.put("activeUrl",activeUrl);
        final String receivers ="shang_ming_yang@163.com";
        emailSender.sendTemplateMail(receivers, "激活账户", "emailtemplates/activeEmail.vm", context, true);
    }

    /**
     * genarate the activeurl
     * @param userName
     * @param activeCode random active code
     * @return the activeUrl
     */
    private String genereateUrlByGet(String userName, String activeCode) {
        return new StringBuffer(HOST_NAME)
                .append("?userName=")
                .append(userName)
                .append("&activeCode=")
                .append(activeCode)
                .toString();
    }

}