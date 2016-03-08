package com.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yj on 14-11-24.
 * json类型controller
 * RestController 注解下的所有方法直接返回json格式数据
 */
@RestController
@RequestMapping("/json")
public class TestController {

    @RequestMapping("/bean")
    public Bean getBean(){
        Bean bean = new Bean();
        bean.setId("123");
        bean.setName("bean");
        return bean;
    }

    class Bean{
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
