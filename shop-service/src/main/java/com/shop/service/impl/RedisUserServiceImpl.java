package com.shop.service.impl;

import com.shop.dao.impl.RedisDao;
import com.shop.domain.User;
import com.shop.service.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qf on 2014/11/28.
 */
@Service("redisUserService")
public class RedisUserServiceImpl implements RedisUserService {
    @Autowired
    private RedisDao redisDao;


    /**
     * 根据email查找redis中的User
     * @param email
     * @return User对象
     */
    @Override
    public User findUserByEmail(String email) {
        User user=new User();
        return (User)redisDao.getObject(email);
    }

    /**
     * 根据userName查找redis中的User
     * @param userName
     * @return User对象
     */
    @Override
    public User findUserByUserName(String userName) {
        User user=new User();
        return (User)redisDao.getObject(userName);
    }

    /**
     * 创建user记录
     * @param user
     * @return user主键
     */
    @Override
    public String createUser(User user) {
        String email=user.getEmail();
        String userName=user.getUserName();
        redisDao.saveObject(email,user);
        redisDao.saveObject(userName,user);
        return "";
    }

}
