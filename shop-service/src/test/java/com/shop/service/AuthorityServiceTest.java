package com.shop.service;

import com.shop.domain.Authority;
import com.shop.domain.Role;
import com.shop.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by ldz on 27/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/shop-service.xml"})
public class AuthorityServiceTest {
    @Autowired
    private AuthorityService authorityService;
    private Role role= new Role();
    private User user= new User();
    public AuthorityService getAuthorityService() {
        return authorityService;
    }

    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    public void initData(){
        //O(∩_∩)O我直接复制数据库的东西
        user.setId("3e17ef31b0c3466fa10a763caf8e4ff1");
        role.setId("ff80818149efe2da0149efe2eb400001");
    }

    @Test
    public void createAuthorityTest(){
        initData();
        Authority authority = new Authority();
        String roleId = role.getId();
        String userId = user.getId();
        authority.setRoleId(roleId);
        authority.setUserId(userId);
        authority.setCreateTime(new Date());
        authorityService.createAuthority(authority);

    }
}
