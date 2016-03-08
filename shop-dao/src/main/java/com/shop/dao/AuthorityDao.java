package com.shop.dao;

import com.shop.domain.Authority;

/**
 * 权限Dao
 * Created by ldz on 26/11/14.
 */
public interface AuthorityDao {


    /**
     * 根据id查看权限
     * @param id
     * @return
     */
    Authority getAuthorityById(String id);

    /**
     * 根据userId获取权限表
     * @param pageNo 页号
     * @param pageSize 每页大小
     * @return
     */
    Page findAuthorityByUserId(String userId,int pageNo, int pageSize);


    /**
     * 根据roleId获取权限
     * @param roleId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page findAuthorityByRoleId(String roleId,int pageNo, int pageSize);

    /**
     * 创建权限
     * @param authority
     * @return authorityID
     */
    String createAuthority(Authority authority);
}
