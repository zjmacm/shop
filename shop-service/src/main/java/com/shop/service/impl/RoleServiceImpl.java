package com.shop.service.impl;

import com.shop.dao.RoleDao;
import com.shop.domain.Role;
import com.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ldz on 27/11/14.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
   @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public String createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleDao.getRoleByRoleName(roleName);
    }

    @Override
    public String getIdByRoleName(String roleName) {
        return roleDao.getIdByRoleName(roleName);
    }
}
