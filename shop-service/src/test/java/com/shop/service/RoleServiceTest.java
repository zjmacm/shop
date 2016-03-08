package com.shop.service;

import com.shop.domain.Role;
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
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void createRoleTest(){
        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ROLE_ADMIN");
        roleAdmin.setCreateTime(new Date());
        roleAdmin.setDefaultLevel(10);
        roleService.createRole(roleAdmin);
        Role roleUser = new Role();
        roleUser.setRoleName("ROLE_USER");
        roleUser.setCreateTime(new Date());
        roleUser.setDefaultLevel(5);
        roleService.createRole(roleUser);
        Role roleSeller = new Role();
        roleSeller.setRoleName("ROLE_SELLER");
        roleSeller.setCreateTime(new Date());
        roleSeller.setDefaultLevel(8);
        roleService.createRole(roleSeller);
    }

}
