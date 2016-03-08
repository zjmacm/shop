package com.shop.service.impl;

import com.shop.domain.User;
import com.shop.service.UserContext;
import com.shop.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NOTE: This is no longer used. See {@link com.shop.service.impl.SpringSecurityUserContextImpl}.
 *
 * Returns the same user for every call to {@link #getCurrentUser()}. This is used prior to adding security, so that the
 *
 * Created by ldz on 24/11/14.
 *
 * @see com.shop.service.impl.SpringSecurityUserContextImpl
 */
//@Component
public class UserContextImpl implements UserContext {
    private final UserService userService;
    /**
     * The {@link User#getId()} for the user that is representing the currently logged in user. This can be
     * modified using {@link #setCurrentUser(User)}
     */
    private String currentUserId ;
    //日志记录器
    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
        public UserContextImpl(UserService userService) {
        if (userService == null) {
            throw new IllegalArgumentException("userService cannot be null");
        }
        this.userService = userService;
    }

    @Override
    public User getCurrentUser() {
        return userService.getUser(currentUserId);
    }

    @Override
    public void setCurrentUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        String currentId = user.getId();
        if(currentId == null) {
            throw new IllegalArgumentException("user.getId() cannot be null");
        }
        this.currentUserId = currentId;
    }
}