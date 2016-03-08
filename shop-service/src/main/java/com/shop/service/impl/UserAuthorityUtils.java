package com.shop.service.impl;

import com.shop.dao.impl.HibernateDao;
import com.shop.domain.Authority;
import com.shop.domain.Role;
import com.shop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A utility class used for creating the {@link org.springframework.security.core.GrantedAuthority}'s given a {@link com.shop.domain.User}. In a real solution
 * this would be looked up in the existing system, but for simplicity our original system had no notion of authorities.
 *
 *
 * Created by ldz on 24/11/14.
 */
@Component("userAuthorityUtils")
public final class UserAuthorityUtils {
    @Autowired
    private  HibernateDao hibernateDao;


    public  Collection<? extends GrantedAuthority> createAuthorities(User user) {
        final String hql = "from Authority authority where authority.userId=?";
        String userId = user.getId();


        List <Authority>authoritiesList= hibernateDao.find(hql,userId);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Authority i:authoritiesList){
            Role role = getRoleById(i.getRoleId());
            GrantedAuthorityImpl auth=new GrantedAuthorityImpl(role.getRoleName());
            authorities.add(auth);
        }
        return authorities;

    }
    /**
     * 从数据库取得Role
     * @param id
     * @return
     */
    private  Role getRoleById(String id){
        String hql = "from Role role where role.id=?";
        List roles =hibernateDao.find(hql,id);
        if (roles!=null||roles.size()!=0){
            return (Role)roles.get(0);
        }
        return null;
    }

    private UserAuthorityUtils(){
    }
}
