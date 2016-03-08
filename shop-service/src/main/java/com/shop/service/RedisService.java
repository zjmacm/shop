package com.shop.service;

/**
 * Created by qf on 2014/11/29.
 */
public interface RedisService<K,V> {
    /**
     * 以json格式序列化对象,保存在缓存中
     */
    public void setObjectToJson( K key,  V value);

    /**
     * 从redis中获取json格式的对象
     * */
    public Object getObjectFromJson(K key,Class<V> clazz);

    /**
     * 以json格式保存对象并获取json字符串
     * */
    public String setObject2Json(K key,V value);

    /**
     * 获取保存的对象的json格式数据
     * */
    public String getObjectAsJson(String key);

    /**
     * 将对象添加到一个定长的list
     * 从列头添加数据,列尾超出指定长度的数据被清除
     * @param list 列名
     * @param object 新添加的数据
     * @param length 列表长度
     * */
    public void setToList(final String list, final Object object, final int length);

    /**
     *从指定的List中获取数据
     * @param list 列名
     * @param index 下标
     * */
    public Object getFromList(final String list, final int index);
}
