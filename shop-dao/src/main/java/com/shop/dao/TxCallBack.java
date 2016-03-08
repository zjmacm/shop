package com.shop.dao;

import org.springframework.data.redis.connection.RedisConnection;

/**
 * Created by yj on 11/21/14.
 * redis事务回调接口
 */
public interface TxCallBack{
    /**
     * 主要的回调方法 在此方法中实现事务的具体操作
     * @param ops 模板传递进来的操作对象
     * 调用方法例如：
     *  ops.opsForValue().increment("count1",1000);
     * */
    public void callback(RedisConnection ops);
}
