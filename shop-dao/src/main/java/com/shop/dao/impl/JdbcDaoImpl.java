package com.shop.dao.impl;

import com.shop.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yj on 14-11-29.
 *
 * 利用JdbcTemplate 封装常见查询操作
 */
public class JdbcDaoImpl {

    @Autowired
    private JdbcTemplate template;


    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public  RowMapper<Goods> getGoodsMapper(){
        return new Mapper();
    }

    /**
     * 商品的简单mapper
     * 包含字段 id name price description
     * */
    private class Mapper implements RowMapper <Goods>{

        @Override
        public Goods mapRow(ResultSet resultSet, int i) throws SQLException {
            com.shop.domain.Goods goods = new com.shop.domain.Goods();
            goods.setId(resultSet.getString("id"));
            goods.setName(resultSet.getString("name"));
            goods.setPrice(resultSet.getDouble("price"));
            goods.setDescription(resultSet.getString("description"));
            return goods;
        }
    }
}
