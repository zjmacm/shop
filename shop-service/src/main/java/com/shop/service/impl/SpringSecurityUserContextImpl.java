package com.shop.service.impl;

import java.util.Collection;

import com.shop.domain.User;
import com.shop.service.UserContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * An implementation of {@link com.shop.service.UserContext} that looks up the {@link User} using the Spring Security's
 * {@link org.springframework.security.core.Authentication} by principal name.
 * Created by ldz on 24/11/14.
 */
@Component("userContext")
public class SpringSecurityUserContextImpl implements UserContext {
    @Autowired
    private ShopUserDetails shopUserDetails;
    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;
    //日志记录器
    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private UserAuthorityUtils userAuthorityUtils;
    /**
     * Get the {@link User} by casting the {@link org.springframework.security.core.Authentication}'s principal to a {@link User}.
     */
    @Override
    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (ShopUserDetails) authentication.getPrincipal();

    }

    /**
     * Sets the {@link User} as the current {@link org.springframework.security.core.Authentication}'s principal. It uses
     */
    @Override
    public void setCurrentUser(User user) {
        if (user == null) {
            log.debug("user cannot be null");
            throw new IllegalArgumentException("user cannot be null");
        }
        Collection<? extends GrantedAuthority> authorities =  userAuthorityUtils.createAuthorities(user);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(shopUserDetails.init(user),user.getPassword(),authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
