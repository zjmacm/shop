package com.shop.dao.impl;

import com.shop.dao.AuthorityDao;
import com.shop.dao.Page;
import com.shop.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by ldz on 26/11/14.
 */
@Repository("authorityDao")
public class AuthorityDaoImpl implements AuthorityDao {
    @Autowired
    private HibernateDao hibernateDao;
    @Autowired
    private JdbcTemplate  jdbcTemplate;

    public JdbcTemplate getTemplate() {
        return jdbcTemplate;
    }

    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HibernateDao getHibernateDao() {
        return hibernateDao;
    }

    public void setHibernateDao(HibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }

    @Override
    public Authority getAuthorityById(String id) {
        return hibernateDao.load(Authority.class,id);
    }

    @Override
    public Page findAuthorityByUserId(String userId, int pageNo, int pageSize) {
        final String hql ="select * from shop_authority where user_id=?";
        return hibernateDao.pagedQuery(hql,pageNo,pageSize,userId);
    }

    @Override
    public Page findAuthorityByRoleId(String roleId, int pageNo, int pageSize) {
        final String hql ="select * from shop_authority where role_id=?";
        return hibernateDao.pagedQuery(hql,pageNo,pageSize,roleId);
    }

    @Override
    public String createAuthority(Authority authority) {
        return String.valueOf(hibernateDao.save(authority));
    }
}
