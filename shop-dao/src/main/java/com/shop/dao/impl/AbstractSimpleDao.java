package com.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.dao.*;

public abstract class AbstractSimpleDao extends BaseDaoImpl implements SimpleDaoJDBC {

    public <T> void create(T o) {
        DomainWrap<T> dw = new DomainWrap<T>(o);
        create(dw.getTableName(), dw.getRowMapForCreate());
    }

    /**
     * retriv 更新
     * @param o
     * @param <T>
     */
    public <T> void retrieve(T o) {
        DomainWrap<T> dw = new DomainWrap<T>(o);
        String tbName = dw.getTableName();
        Map<String, Object> conds = dw.getIdMap();
        List<Object> plist = new ArrayList<Object>();
        //List<Object> plist = new ArrayList<Object>();

        //数据库查询语句
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        // sb.append(tbName.toUpperCase()).append(" WHERE ");
        sb.append(tbName.toLowerCase()).append(" WHERE ");

        for (String key : conds.keySet()) {
            // sb.append(key.toUpperCase()).append('=');
            sb.append(key.toLowerCase()).append('=');
            Object obj = conds.get(key);
            sb.append('?');
            plist.add(obj);
            sb.append(" AND ");
        }
        sb.delete(sb.length() - 5, sb.length() - 1);
        getJdbcTemplate().queryForObject(sb.toString(), plist.toArray(), dw);
    }

    public <T> int update(T o) {
        DomainWrap<T> dw = new DomainWrap<T>(o);
        return update(dw.getTableName(), dw.getRowMap(), dw.getIdMap());
    }

    public <T> int delete(T o) {
        DomainWrap<T> dw = new DomainWrap<T>(o);
        return delete(dw.getTableName(), dw.getIdMap());
    }

    public <T> int updateIgnoreNull(T o) {
        DomainWrap<T> dw = new DomainWrap<T>(o);
        return update(dw.getTableName(), dw.getRowMapIgnoreNull(), dw.getIdMap());
    }

    public <T> List<T> listAll(Class<T> c) {
        DomainWrap<T> dw = new DomainWrap<T>(c);
        StringBuffer sb = new StringBuffer("SELECT * FROM ").append(dw.getTableName());
        return getJdbcTemplate().query(sb.toString(), dw);
    }

    public <T> List<T> listAll(Class<T> c, Order o) {
        DomainWrap<T> dw = new DomainWrap<T>(c);
        StringBuffer sb = new StringBuffer("SELECT * FROM ").append(dw.getTableName()).append(o.toSqlString());
        return getJdbcTemplate().query(sb.toString(), dw);
    }

    protected int getInteger(Map<String, ?> params, String keyName, int defaultValue) {
        if (params.containsKey(keyName)) {
            return Integer.parseInt(params.get(keyName).toString());
        } else {
            return defaultValue;
        }
    }

    public <T> PageJdbc<T> pageQuery(Class<T> c, String condition, Map<String, ?> params, int pageIndex, int pageSize,
                                 Order order) {

        DomainWrap<T> dw = new DomainWrap<T>(c);
        String sql = "SELECT * FROM " + dw.getTableName();
        if (null == condition || "".equals(condition))
            sql += " WHERE " + condition;

        return pageQuery(sql, params, pageIndex, pageSize, order, c);
    }

    public <T> PageJdbc<T> pageQuery(Class<T> c, String condition, Map<String, ?> params, Order order) {

        int pageindex = getInteger(params, BaseConstant.PAGE_INDEX, BaseConstant.DEF_PAGE_INDEX);
        // pageindex++;
        int pagesize = getInteger(params, BaseConstant.PAGE_SIZE, BaseConstant.DEF_PAGE_SIZE);

        return pageQuery(c, condition, params, pageindex, pagesize, order);
    }

    public <T> PageJdbc<T> pageQuery(String sql, Map<String, ?> params, Order order, Class<T> c) {

        int pageindex = getInteger(params, BaseConstant.PAGE_INDEX, BaseConstant.DEF_PAGE_INDEX);
        // pageindex++;

        int pagesize = 10;

        return pageQuery(sql, params, pageindex, pagesize, order, c);
    }

    //start=20&limit=10
    public PageJdbc<Map<String, Object>> pageQuery(String sql, Map<String, ?> params, Order order) {

        int pageindex = getInteger(params, BaseConstant.PAGE_INDEX, BaseConstant.DEF_PAGE_INDEX);
        // pageindex++;
        int pagesize = getInteger(params, BaseConstant.PAGE_SIZE, BaseConstant.DEF_PAGE_SIZE);

        //不需要页码，所以pageindex不考虑
        pageindex = 0;

        return pageQuery(sql, params, pageindex, pagesize, order); // to-do
    }
}
