package com.shop.service.impl;

import com.shop.dao.impl.RedisDao;
import com.shop.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qf on 2014/11/29.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisDao redisDao;

    /**
     * 以json格式序列化对象,保存在缓存中
     */
    @Override
    public void setObjectToJson(Object key, Object value) {
        redisDao.saveObject(key, value);
    }

    /**
     * 从redis中获取json格式的对象
     * */
    @Override
    public Object getObjectFromJson(Object key, Class clazz) {
        return redisDao.getObject(key);
    }

    /**
     * 以json格式保存对象并获取json字符串
     * */
    @Override
    public String setObject2Json(Object key, Object value) {
        return redisDao.saveObject(key, value);
    }

    /**
     * 获取保存的对象的json格式数据
     * */
    @Override
    public String getObjectAsJson(String key) {
        return redisDao.getObjectAsJson(key);
    }

    /**
     * 将对象添加到一个定长的list
     * 从列头添加数据,列尾超出指定长度的数据被清除
     * @param list 列名
     * @param object 新添加的数据
     * @param length 列表长度
     * */
    @Override
    public void setToList(String list, Object object, int length) {
        redisDao.maintainListAdd(list, object, length);
    }

    /**
     *从指定的List中获取数据
     * @param list 列名
     * @param index 下标
     * */
    @Override
    public Object getFromList(String list, int index) {
        return redisDao.listIndex(list, index, null, null);
    }
}
