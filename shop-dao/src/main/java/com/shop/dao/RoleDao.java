package com.shop.dao;

import com.shop.domain.Role;

/**
 * Created by ldz on 26/11/14.
 */
public interface RoleDao {


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

    /**
     * 更新角色时候需要级联更新shop_authority
     * @param role
     */
    void update(Role role);

    /**
     * 删除角色需要级联操作shop_authority
     * @param role
     */
    void remove(Role role);
}
