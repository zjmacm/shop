package com.shop.service.impl;

import com.shop.dao.UserDao;
import com.shop.dao.impl.HibernateDao;
import com.shop.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Integrates with Spring Security using our existing {@link UserDao} by looking up a {@link User} and
 * converting it into a {@link org.springframework.security.core.userdetails.UserDetails} so that Spring Security can do the username/password comparison for us.
 *
 * Created by ldz on 24/11/14.
 *
 * @see UserAuthenticationProvider
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao jdbcUserDao;

    private final HibernateDao hibernateDao;
    @Autowired
    private ShopUserDetails shopUserDetails;
    @Autowired
    private  UserAuthorityUtils userAuthorityUtils;
    //日志记录器
    /**
     * 这个是什么东西
     */
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    public UserDetailsServiceImpl(UserDao jdbcUserDao,HibernateDao hibernateDao) {
        if (jdbcUserDao == null) {
            log.debug("UserDao cannot be null");
            throw new IllegalArgumentException("UserDao cannot be null");
        }
        if(hibernateDao == null) {
            log.debug("UserDao cannot be null");
            throw new IllegalArgumentException("UserDao cannot be null");
        }

        this.jdbcUserDao = jdbcUserDao;
        this.hibernateDao = hibernateDao;
        }

    /**
     * Lookup a {@link User} by the username representing the email address. Then, convert the
     * {@link User} into a {@link org.springframework.security.core.userdetails.UserDetails} to conform to the {@link org.springframework.security.core.userdetails.UserDetails} interface.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("loadUserByUsername"+username);

        User user = jdbcUserDao.findUserByEmail(username);

        System.err.println("loadByUserName:"+user);

        if (user == null) {
            log.debug("Invalid username/password.");
            throw new UsernameNotFoundException("Invalid username/password.");
        }
        return shopUserDetails.init(user);
    }

    /**
     * There are advantages to creating a class that extends {@link User}, our domain notion of a user, and
     * implements {@link org.springframework.security.core.userdetails.UserDetails}, Spring Security's notion of a user.
     * <ul>
     * <li>First we can obtain all the custom information in the {@link User}</li>
     * <li>Second, we can use this service to integrate with Spring Security in other ways (i.e. when implementing
     * Spring Security's <a
     * href="http://static.springsource.org/spring-security/site/docs/3.1.x/reference/remember-me.html">Remember-Me
     * Authentication</a></li>
     * </ul>
     *
     * 
     *
     */

}
