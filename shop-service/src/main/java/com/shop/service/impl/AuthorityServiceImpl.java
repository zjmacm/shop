package com.shop.service.impl;

import com.shop.dao.AuthorityDao;
import com.shop.dao.Page;
import com.shop.domain.Authority;
import com.shop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ldz on 27/11/14.
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityDao authorityDao;

    public AuthorityDao getAuthorityDao() {
        return authorityDao;
    }

    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    @Override
    public Authority getAuthorityById(String id) {
        return authorityDao.getAuthorityById(id);
    }

    @Override
    public Page findAuthorityByUserId(String userId, int pageNo, int pageSize, Object... values) {
        return authorityDao.findAuthorityByUserId(userId,pageNo,pageSize);
    }

    @Override
    public Page findAuthorityByRoleId(String roleId, int pageNo, int pageSize, Object... values) {
        return authorityDao.findAuthorityByRoleId(roleId, pageNo, pageSize);
    }


    @Override
    public String createAuthority(Authority authority) {
        return authorityDao.createAuthority(authority);
    }
}
