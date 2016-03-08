package com.shop.dao;

import com.shop.domain.Goods;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-12-6.
 *
 * 一些通用数据库操作方法
 */
public interface CommonDao {

    /**
     * 商品搜索服务底层API
     * version1.0
     * 支持商品名模糊查询
     * @param name 商品名
     * @return List<Map> map中包含id name description price category(分类名)
     * */
    public List<Map<String,Object>> searchByName(String name);

    /**
     * 过滤方法,按商品分类过滤
     * @param list 被过滤商品
     * @param category 分类
     * */
    public List<Goods> filterByCategory(String category, List<Map<String,Object>> list) throws InvocationTargetException, IllegalAccessException;
}
