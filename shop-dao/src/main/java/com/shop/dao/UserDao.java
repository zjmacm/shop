package com.shop.dao;

import com.shop.domain.User;


/**
 * 用户数据库操作相关接口
 *
 * Created by ldz on 24/11/14.
 */


public interface UserDao {
    /**
     * 获取用户
     * @param id UUID
     * @return
     */
    User getUser(String id);

    /**
     * 通过email获取用户
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 通过username获取用户
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);


    /**
     * 创建用户
     * @param user
     * @return userID
     */
    String createUser(User user);

    /**
     * save user
     * @param entity
     * @return
     */
    String save(Object entity);

    /**
     * update a user
     * @param entity to be updated user
     */
    void update(Object entity);
}
