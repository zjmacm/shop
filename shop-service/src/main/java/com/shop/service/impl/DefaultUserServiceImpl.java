package com.shop.service.impl;

import com.shop.dao.UserDao;
import com.shop.domain.User;
import com.shop.service.Constants;
import com.shop.service.UserContext;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * A default implementation of {@link com.shop.service.UserService} that delegates to{@link UserDao}.
 *
 * Created by ldz on 24/11/14.
 */

@Service("userService")
public class DefaultUserServiceImpl implements UserService {

    @Autowired
    private  UserDao userDao;
    @Autowired
    private UserContext userContext;

    public UserDao getJdbcUserDao() { return userDao; }

    public void setJdbcUserDao(UserDao userDao) { this.userDao = userDao; }

    public User getUser(String id){ return userDao.getUser(id); }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Override
    public String createUser(User user) {
        return userDao.createUser(user);
    }
    @Override

    public boolean register(User user, boolean flag) {
        //find whether the user's name has existed
        if(user == null){return false;}
        User _user = this.userDao.findUserByUserName(user.getUserName());
        //if has existed,return false to notify the name has existed
        if(_user!=null){return false;}
        //not existed,insert it
        user.setUserStatus("0");  // "0"表示现在还没有激活
        final String uuid = this.userDao.createUser(user);
        if(uuid==null){return false;}
        return true;
    }

    @Override
    public boolean active(String userName) {
        User user = this.userDao.findUserByUserName(userName);
        if(user.getUserStatus() == String.valueOf(Constants.WAIT_ENABLE)){
            user.setUserStatus(String.valueOf(Constants.ENABLE));
            return this.userDao.save(user)!=null;
        }
        return false;
    }
}