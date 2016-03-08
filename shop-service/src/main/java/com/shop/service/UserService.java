package com.shop.service;

import com.shop.domain.User;

/**
 * Created by ldz on 24/11/14.
 */
public interface UserService {

    /**
     * 根据Id查找User
     * @param id
     * @return User对象
     */
    User getUser(String id);

    /**
     * 根据email查找User
     * @param email
     * @return User对象
     */
    User findUserByEmail(String email);

    /**
     * 根据userName查找User
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 创建user记录
     * @param user
     * @return user主键
     */
    String createUser(User user);

    /**
     * 用户（买家和卖家）注册
     * @param  user
     * @param flag buyer or saler
     * @return uuid
     */
    boolean register(User user,boolean flag);

    /**
     *
     * @param userName
     * @return
     */
    boolean active(String userName);




}