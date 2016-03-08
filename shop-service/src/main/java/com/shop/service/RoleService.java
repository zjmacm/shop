package com.shop.service;

import com.shop.domain.Role;

/**
 * Created by ldz on 27/11/14.
 */
public interface RoleService {
    /**
     * 根据id查看角色
     * @param id
     * @return
     */
    Role getRoleById(String id);

    /**
     * 根据roleName查看角色
     * @param roleName
     * @return
     */
    Role getRoleByRoleName(String roleName);

    /**
     * 根据角色名查看角色id
     * @param roleName
     * @return
     */
    String getIdByRoleName(String roleName);

    /**
     * 创建角色
     * @param role
     * @return userID
     */
    String createRole(Role role);
}
