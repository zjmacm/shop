package com.shop.service.impl;

import com.shop.domain.User;
import com.shop.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * A Spring Security {@link org.springframework.security.authentication.AuthenticationProvider} that uses our {@link com.shop.service.UserService} for authentication. Compare
 * this to our {@link com.shop.service.impl.UserDetailsServiceImpl} which is called by Spring Security's {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.

 * Created by ldz on 24/11/14.
 *@see com.shop.service.impl.UserDetailsServiceImpl
 */
@Service("userAuthenticationProvider")
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    @Autowired
    private ShopUserDetails shopUserDetails;
    //日志记录
    @Autowired
    private UserAuthorityUtils userAuthorityUtils;
    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    public UserAuthenticationProvider(UserService userService) {
        if (userService == null) {
            throw new IllegalArgumentException("userService cannot be null");
        }
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String email = token.getName();
        System.err.println("emailAuthenticate"+email);
        User user = email == null ? null : userService.findUserByEmail(email);
        if(user == null) {
            log.debug("Invalid username/password");
            throw new UsernameNotFoundException("Invalid username/password");
        }
        String password = user.getPassword();

        if(!password.equals(token.getCredentials())) {
            log.debug("Invalid username/password");
            throw new BadCredentialsException("Invalid username/password");
        }

        Collection<? extends GrantedAuthority> authorities =userAuthorityUtils.createAuthorities(shopUserDetails.init(user));

        return new UsernamePasswordAuthenticationToken(shopUserDetails.init(user), password, authorities);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
