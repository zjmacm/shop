package com.shop.dao.impl;

import com.shop.dao.CategoryDao;
import com.shop.domain.Category;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-26.
 * @see com.shop.dao.CategoryDao
 * 商品分类操作实体类
 */
@Repository("CategoryDao")
public class CategoryDaoImpl extends HibernateDao implements CategoryDao{

    private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    private final Log log = LogFactory.getLog(this.getClass());

    private static final String QUERY_BY_ID = "SELECT ID, CATEGORY, PARENT_ID, CREATE_TIME " +
            "FROM shop_category WHERE ID = ?";

    private static final String QUERY_BY_NAME = "SELECT ID, CATEGORY, PARENT_ID, CREATE_TIME " +
            "FROM shop_category WHERE CATEGORY = ?";


    private static final String GET_ALL_PARENT_CATEGORY = "SELECT  id,parent_id,category,create_time WHERE parent_id= ?";
    private static final String GET_ALL = "SELECT ID, CATEGORY, PARENT_ID, CREATE_TIME FROM shop_category GROUP BY category,parent_id";
    private static final String GET_CHILDREN_BY_CATEGORY = "SELECT  id,parent_id,category,create_time WHERE parent= ?";


    @Autowired
    public CategoryDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }


    @Override
    public String create(Category category) {
        if(null == category || null == category.getCategory()) {
            log.debug("category cannot be null");
            throw new IllegalArgumentException("category cannot be null");
        }
        return save(category);
    }

    @Override
    public void update(Category category) {
        if(null == category || null == category.getId()) {
            log.debug("category cannot be null");
            throw new IllegalArgumentException("category cannot be null");
        }
        super.update(category);
    }

    /*
    * 可以根据id或者category删除
    * */
    @Override
    public void remove(final Category category) {
        if(null == category ||
                (null == category.getId() && null == category.getCategory())) {
            log.debug("category cannot be null");
            throw new IllegalArgumentException("category cannot be null");
        }
        if( category.getId() != null)
            super.remove(category);
        else {
            Boolean result = getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
                @Override
                public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
                    String hql = "delete Category c where  c.category = ?";
                    Query query = session.createQuery(hql);
                    query.setString(0,category.getCategory());
                    query.executeUpdate();
                    return null;
                }
            });
        }
    }

    /**
    * 通过分组查询获取所有分类信息
    * @return map中包含category和parent
    * */
    @Override
    public List<Map<String,Object>> getAllCategory() {
        return jdbcTemplate.queryForList(GET_ALL);
    }

    @Override
    public Category getCategoryById(String id) {
       if(null == id || id.equals("")) {
           log.debug("id cannot be null");
           throw new IllegalArgumentException("id cannot be null");
       }
       CategoryMappingQuery mappingQuery = new CategoryMappingQuery(dataSource,
              QUERY_BY_ID,"id",Types.VARCHAR);
       return mappingQuery.findObject(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        if(null == name || name.equals("")) {
            log.debug("name cannot be null");
            throw new IllegalArgumentException("name cannot be null");
        }
        CategoryMappingQuery mappingQuery = new CategoryMappingQuery(dataSource,
                QUERY_BY_NAME,"category",Types.VARCHAR);
        return mappingQuery.findObject(name);
    }


    @Override
    public List<Map<String,Object>> getChildByCategory(Category parent) {
        return jdbcTemplate.queryForList(GET_CHILDREN_BY_CATEGORY,parent.getId());
    }

    @Override
    public List<Map<String,Object>> getAllParentCategory() {
        return jdbcTemplate.queryForList(GET_ALL_PARENT_CATEGORY);
    }

    /**
     * JDBC查询封装实体
     *
     * @see org.springframework.jdbc.object.MappingSqlQuery
     * */
    private class CategoryMappingQuery extends MappingSqlQuery<Category>{

        /**
         * @param ds 注入数据源
         * @param sql 查询语句
         * @param queryColumn 筛选列名 如按id查询则传入"id"
         * @param columnType 筛选列名的类型 javax.sql.Types内置类型(int)
         * */
        public CategoryMappingQuery(DataSource ds, String sql,
                                    String queryColumn,int columnType ){
            super(ds, sql);
            super.declareParameter(new SqlParameter(queryColumn, columnType));
            compile();
        }

        public CategoryMappingQuery(DataSource ds, String sql){
            super(ds, sql);
            compile();
        }

        @Override
        protected Category mapRow(ResultSet resultSet, int i) throws SQLException {
            Category category = new Category();
            category.setId(resultSet.getString("id"));
            category.setCategory(resultSet.getString("category"));
            category.setParentId(resultSet.getString("parent_id"));
            category.setCreateTime(resultSet.getDate("create_Time"));
            return category;
        }

    }


}
