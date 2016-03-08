package com.shop.dao.impl;


import com.alibaba.fastjson.JSON;
import com.shop.dao.TxCallBack;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 11/21/14.
 *
 * RedisDao基类 供继承和直接调用
 * 底层序列化 key使用StringRedisSerializer
 *          value 使用JdkSerializationRedisSerializer
 */

@Repository("RedisDao")
public class RedisDao<K,V> {

    Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "redisTemplate")
    RedisTemplate<K,V> template;

    StringRedisSerializer keySerializer = new StringRedisSerializer();

    JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

    public RedisTemplate getTemplate() {
        return template;
    }

    /**
     * 事务方法，事务中要执行的操作在回调函数中实现
     *
     * @param callBack 事务要执行的操作,以回调接口形式实现
     */
    public Object transaction(final TxCallBack callBack) {
        Object result =  template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.multi();
                callBack.callback(redisConnection);
                return redisConnection.exec();
            }
        });
        return result;
    }

    /**
     * 保存字符串类型数据,指定过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间
     * @param unit  时间单位
     */
    public void saveString(K key, V value, long time, TimeUnit unit) {
        template.opsForValue().set(key, value, time, unit);
    }

    /**
     * 保存字符串类型数据
     *
     * @param key   键
     * @param value 值
     */
    public void saveString(K key, V value) {
        template.opsForValue().set(key, value);
    }

    //public void saveString(K key, V value) { template.opsForValue().set(key, value); }
    /**
     * 获取字符串类型数据
     *
     * @param key 键
     * @return 值
     */
    public Object getString(K key) {
        return template.opsForValue().get(key);
    }


    /**
     * 通过序列化保存对象
     * 以二进制形式保存
     */
    public String saveObject(final K key, final V value) {
        if (key == null || value == null) {
            log.debug("parameter cannot be null");
            throw new IllegalArgumentException("parameter cannot be null");
        }
        template.boundValueOps(key).set(value);
        return JSON.toJSONString(value);
    }

    /**
     * 保存时带有失效时间
     * */
    public void saveObject(final K key, final V value, final long time,TimeUnit unit) {
        if (key == null || value == null) {
            log.debug("parameter cannot be null");
            throw new IllegalArgumentException("parameter cannot be null");
        }
        template.boundValueOps(key).set(value,time, unit);
    }

    /**
     * 根据key获取保存的对象
     * 从二进制数组反序列化
     */
    public Object getObject(final K key) {
        if (key == null) {
            log.debug("key cannot be null");
            throw new IllegalArgumentException("key cannot be null");
        }
        return template.boundValueOps(key).get();
    }



    /**
     * 获取保存的对象的json格式数据
     * */
    public String getObjectAsJson(K key){
        Object object = template.opsForValue().get(key);
        return JSON.toJSONString(object);
    }


    /**
     * 维护一个定长的list
     * 从列头添加数据,列尾超出指定长度的数据被清除
     * @param list 列名
     * @param object 新添加的数据
     * @param length 列表长度
     * */
    public void maintainListAdd(final K list, final V object, final int length ){
        listAdd(list, object);
        ltrim(list, 0, length);
    }

    public void ltrim(K key,int start, int end){
        template.boundListOps(key).trim(start, end);
    }

    /**
     * 获取一个指定列表的所有成员
     * */
    public List listGetAll(final String list){
        final List result = new ArrayList();
        template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] key = keySerializer.serialize(list);
                long length = redisConnection.lLen(key);
                for(long i = 0; i < length; i++){
                    byte[] value = redisConnection.lIndex(key,i);
                    Object obj = serializer.deserialize(value);
                    result.add(obj);
                }
                return null;
            }
        });
        return result;
    }

    /**
     * 添加整个数组,此操作会清空原数组的所有信息
     * */
    public void listAddAll(final K key, final List<V> list, long expire, TimeUnit unit){
        BoundListOperations<K,V> ops = template.boundListOps(key);
        for(V object : list){
            ops.rightPush(object);
        }
        ops.expire(expire, unit);
    }

    public void listAddAll(K key, V[] values, long expire, TimeUnit unit ){
        listAddAll(key, Arrays.asList(values), expire, unit);
    }

    public  void listAdd(final K key, final V value){
        template.boundListOps(key).leftPush(value);
    }

    public  Object listIndex(final K key, final long index, final Long expire, TimeUnit unit){
        BoundListOperations<K,V> ops = template.boundListOps(key);
        if(expire != null)
            ops.expire(expire, unit);
        return ops.index(index);
    }

    public void setExpire(K key, long expire, TimeUnit unit){
        template.expire(key, expire, unit);
    }

    /**
     * 向有序集合添加和更新元素,通过指定的分数进行排序
     * @param key 有序集合的key
     * @param score 元素的分数
     * @param value 元素
     * */
    public void addZSet(K key, double score, V value){
        template.boundZSetOps(key).add(value, score);
    }

    /**
     * 增长有序集合指定元素的分数
     * */
    public void incrZSet(K key, double score, V value){
        template.boundZSetOps(key).incrementScore(value, score);
    }

    /**
     * 删除有序集合中的指定元素
     * @param key 集合的key
     * @param value 要删除的元素
     * */
    public void removeZSet(K key, V value){
        template.boundZSetOps(key).remove(value);
    }

    public Set getRangeZSet(K key, long start, long end){
        return template.boundZSetOps(key).range(start, end);
    }
}
