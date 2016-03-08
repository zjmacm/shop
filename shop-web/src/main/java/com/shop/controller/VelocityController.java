package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yj on 14-11-24.
 *
 * 用于velocity模板测试
 */
@Controller
@RequestMapping("/vm")
public class VelocityController {

    @RequestMapping("/get")
    public ModelAndView getVM(){
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("message","hello");
       // mav.setView();
        return mav;
    }

}
