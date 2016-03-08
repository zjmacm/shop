package com.shop.dao.impl;

import com.shop.dao.RoleDao;
import com.shop.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by ldz on 26/11/14.
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private HibernateDao hibernateDao;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public HibernateDao getHibernateDao() {
        return hibernateDao;
    }

    public void setHibernateDao(HibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }

    @Override
    public Role getRoleById(String id) {
        return (Role)hibernateDao.get(Role.class,id);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        Role role = template.queryForObject("select * from users where id = 1", Role.class);
        return role;
    }

    @Override
    public String getIdByRoleName(String roleName) {
        return getRoleByRoleName(roleName).getId();
    }

    @Override
    public String createRole(Role role) {
        return hibernateDao.save(role);
    }


    @Override
    public void update(Role role) {
        //难实现，且实现没有太大意思，先放着
        //hibernateDao.update(role);
    }

    @Override
    public void remove(Role role) {
        //难实现，且实现没有太大意思，先放着
        //hibernateDao.remove(role);
    }
}
