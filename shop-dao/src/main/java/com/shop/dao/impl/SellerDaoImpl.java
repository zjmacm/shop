package com.shop.dao.impl;

import com.shop.dao.SellerDao;
import com.shop.domain.Seller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 14-12-3.
 */
@Repository("sellerDao")
public class SellerDaoImpl implements SellerDao{

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

    private static final String SELLER_QUERY = "select * from shop_seller where ";
    private static final String ROLE_QUERY = "select * from shop_authority where";
    private static final RowMapper<Seller> SELLER_MAPPER = new SellerRowMapper();

    @Autowired
    public SellerDaoImpl(JdbcOperations jdbcOperations) {
        if (jdbcOperations == null) {
            throw new IllegalArgumentException("jdbcOperations cannot be null");
        }
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Seller getSeller(String id) {
        return jdbcOperations.queryForObject(SELLER_QUERY + "id = ?",SELLER_MAPPER , id);
    }

    @Override
    public Seller findSellerByEmail(String email) {
        if (email == null) {
            log.debug("email cannot be null");
            throw new IllegalArgumentException("email cannot be null");

        }
        try {
            return jdbcOperations.queryForObject(SELLER_QUERY + "email = ?", SELLER_MAPPER, email);
        } catch (EmptyResultDataAccessException notFound) {
            log.debug("findSellerByEmail Exception!");
            return null;
        }
    }

    @Override
    public Seller findSellerBySellerName(String sellerName) {
        if(sellerName==null){
            throw new IllegalArgumentException("sellerName cannot be null");
        }
        try {
            return jdbcOperations.queryForObject(SELLER_QUERY + "seller_name = ?", SELLER_MAPPER, sellerName);
        }catch (EmptyResultDataAccessException notFound){
            log.debug("findBySellerName Exception!");
            return null;
        }
    }

    @Override
    public String createSeller(Seller seller) {
        return null;
    }

    @Override
    public String save(Object entity) {
        return this.hibernateDao.save(entity);
    }

    @Override
    public void update(Object entity) {
        this.hibernateDao.update(entity);
    }

    /**
     *数据库结果封装成对象Seller
     */
    static class SellerRowMapper implements RowMapper<Seller> {


        public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
            Seller seller = new Seller();
            seller.setId(rs.getString("id"));
            seller.setSellerName(rs.getString("seller_name"));
            seller.setOwnerName(rs.getString("owner_name"));
            seller.setRegisterDate(rs.getDate("register_date"));
            seller.setPhone(rs.getString("phone"));
            seller.setBirthPlace(rs.getString("birth_place"));
            seller.setGender(rs.getString("gender"));
            seller.setMobile(rs.getString("mobile"));
            seller.setLiving(rs.getString("living"));
            seller.setSellerStatus(rs.getString("seller_status"));
            seller.setBirthday(rs.getDate("birthday"));
            seller.setEmail(rs.getString("email"));
            seller.setPassword(rs.getString("password"));
            seller.setIcon(rs.getString("icon"));
            return seller;
        }
    };
}
