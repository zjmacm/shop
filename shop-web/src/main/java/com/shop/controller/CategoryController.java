package com.shop.controller;

import com.alibaba.fastjson.JSON;
import com.shop.dao.impl.RedisDao;
import com.shop.domain.User;
import com.shop.service.CategoryService;
import com.shop.service.impl.CategoryServiceImpl.Hierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by yj on 14-11-26.
 *
 * 商品分类路由控制器
 */


@Controller
@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private RedisDao<String,String> redisDao;




    //如果返回已经转换好的json格式字符串,需要设置produce格式 否则中文乱码
    @RequestMapping(produces = {"application/json;charset=UTF-8"},value = "/all")
    private String getAll(){
        //先查询缓存
        String json = (String)redisDao.getString("category.all");
        //未命中
        if(json == null) {
            Map<String, Hierarchy> all = service.getAll();
            List<Hierarchy> result = new ArrayList<Hierarchy>();
            Set<String> keys = all.keySet();
            for (String key : keys) {
                result.add(all.get(key));
            }
            json = JSON.toJSONString(result);
            System.out.println(json);
            //写入缓存
            redisDao.saveString("category.all",json);
        }
        return json;
    }
}
