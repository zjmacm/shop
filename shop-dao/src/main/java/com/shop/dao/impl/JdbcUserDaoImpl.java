package com.shop.dao.impl;


import com.shop.dao.UserDao;
import com.shop.domain.User;
import com.shop.utils.Tool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * A jdbc implementation of {@link com.shop.dao.UserDao}.
 * Created by ldz on 24/11/14.
 */
@Repository("jdbcUserDao")
public class JdbcUserDaoImpl implements UserDao {

    //日志记录
    private Log log = LogFactory.getLog(getClass());

    private final JdbcOperations jdbcOperations;
    @Autowired
    private HibernateDao hibernateDao;

    public HibernateDao getHibernateDao() {
        return hibernateDao;
    }

    public void setHibernateDao(HibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }

    @Autowired
    public JdbcUserDaoImpl(JdbcOperations jdbcOperations) {
        if (jdbcOperations == null) {
            throw new IllegalArgumentException("jdbcOperations cannot be null");
        }
        this.jdbcOperations = jdbcOperations;
    }




    @Override
    public User getUser(String id) {
        return jdbcOperations.queryForObject(USER_QUERY + "id = ?", USER_MAPPER, id);
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            log.debug("email cannot be null");
            throw new IllegalArgumentException("email cannot be null");

        }
        try {
            return jdbcOperations.queryForObject(USER_QUERY + "email = ?", USER_MAPPER, email);
        } catch (EmptyResultDataAccessException notFound) {
            log.debug("findUserByEmail Exception!");
            return null;
        }
    }
    @Override
    public User findUserByUserName(String userName) {
        if(userName==null){
            throw new IllegalArgumentException("userName cannot be null");
        }
        try {
            return jdbcOperations.queryForObject(USER_QUERY + "user_name = ?", USER_MAPPER, userName);
        }catch (EmptyResultDataAccessException notFound){
            log.debug("findByUserName Exception!");
            return null;
        }
    }

    @Override
    public String createUser(final User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        if (user.getId() == null) {
            user.setId(Tool.getUUID32());
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcOperations.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into shop_user (id,user_name,real_name,register_date," +
                                "phone,mobile,gender,email,user_status, password,icon," +
                                "birthday,school,department,grade,third_account,third_token,third_party) " +
                                "values (?,?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new String[] { "id" });
                ps.setString(1, user.getId());
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getRealName());
                ps.setDate(4, new Date(System.currentTimeMillis()));
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getMobile());
                ps.setString(7, user.getGender());
                ps.setString(8, user.getEmail());
                ps.setString(9, user.getUserStatus());
                ps.setString(10, user.getPassword());
                ps.setString(11, user.getIcon());
                ps.setDate(12, new Date(System.currentTimeMillis()));
                ps.setString(13, user.getSchool());
                ps.setString(14, user.getDepartment());
                ps.setString(15,user.getGrade());
                ps.setString(16, user.getThirdAccount());
                ps.setString(17, user.getThirdToken());
                ps.setString(18, user.getThirdParty());


                return ps;
            }
        }, keyHolder);
        //先暂时这样写，不确定是否是toString（）
        return user.getId();
        /*return hibernateDao.save(user);*/
    }

    @Override
    public String save(Object entity) {
        return this.hibernateDao.save(entity);
    }

    @Override
    public void update(Object entity) {
        this.hibernateDao.update(entity);
    }

    // --- non-public static members ---

    private static final String USER_QUERY = "select * from shop_user where ";
    private static final String ROLE_QUERY = "select * from shop_authority where";
    private static final RowMapper<User> USER_MAPPER = new UserRowMapper();

    /**
     *数据库结果封装成对象User
     */
    static class UserRowMapper implements RowMapper<User> {


        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setUserName(rs.getString("user_name"));
            user.setRealName(rs.getString("real_name"));
            user.setRegisterDate(rs.getDate("register_date"));
            user.setPhone(rs.getString("phone"));
            user.setSchool(rs.getString("school"));
            user.setGender(rs.getString("gender"));
            user.setMobile(rs.getString("mobile"));
            user.setDepartment(rs.getString("department"));
            user.setGrade(rs.getString("grade"));

            user.setUserStatus(rs.getString("user_status"));
            user.setBirthday(rs.getDate("birthday"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setIcon(rs.getString("icon"));
            user.setThirdParty(rs.getString("third_party"));
            user.setThirdToken(rs.getString("third_token"));
            user.setThirdAccount(rs.getString("third_account"));
            return user;
        }
    };
}