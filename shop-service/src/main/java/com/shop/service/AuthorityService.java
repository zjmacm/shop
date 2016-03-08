package com.shop.service;

import com.shop.dao.Page;
import com.shop.domain.Authority;

/**
 * Created by ldz on 27/11/14.
 */
public interface AuthorityService {
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
     * @param values 参数
     * @return
     */
    Page findAuthorityByUserId(String userId,int pageNo, int pageSize, Object... values);


    /**
     * 根据roleId获取权限
     * @param roleId
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    Page findAuthorityByRoleId(String roleId,int pageNo, int pageSize, Object... values);

    /**
     * 创建用户
     * @param authority
     * @return authorityID
     */
    String createAuthority(Authority authority);
}
