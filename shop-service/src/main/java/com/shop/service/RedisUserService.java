package com.shop.service;

import com.shop.domain.User;

/**
 * Created by qf on 2014/11/28.
 */
public interface RedisUserService {
    /**
     * 根据email查找redis中的User
     * @param email
     * @return User对象
     */
    public User findUserByEmail(String email);

    /**
     * 根据userName查找redis中的User
     * @param userName
     * @return User对象
     */
    public User findUserByUserName(String userName);

    /**
     * 创建user记录
     * @param user
     * @return user主键
     */
    public String createUser(User user);
}
