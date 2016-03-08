package com.shop.dao.test;

import com.shop.domain.User;
import com.shop.utils.Tool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class JdbcTempTest {

    @Autowired
    JdbcTemplate template;

    @Test
    public void insertTest(){
        String id = Tool.getUUID32();
        int res = template.update("insert into shop_user (id,user_name,real_name,email,password)" +
                " values (?,?,?,?,?)",id,"yijun","ho","383548557@qq.com","123456");
        System.out.println(res);
    }

    @Test
    public void updateTest(){
        template.update("update shop_user set phone = ?","18742513532");
    }

    @Test
    public void deleteTest(){
        template.update("delete from shop_user where user_name = ?","yijun");
    }

    @Test
    public void queryCountTest(){
        int count = template.queryForObject("select count(*) from shop_user",Integer.class);
        System.out.println(count);
    }

    @Test
    public void querySingleObjectTest(){
        //查询返回是多个值时抛异常
        User user = template.queryForObject(
                "select user_name,real_name,email from shop_user where user_name = ?",
                new Object[]{"donahue"},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        User user = new User();
                        user.setEmail(resultSet.getString("email"));
                        user.setRealName(resultSet.getString("real_name"));
                        user.setUserName(resultSet.getString("user_name"));
                        return user;
                    }
                });
        System.out.println(user);
    }

    @Test
    public void queryObjectsTest(){
        List<User> list = template.query(
                "select user_name,real_name,email from shop_user where user_name = 'yijun'",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        User user = new User();
                        user.setEmail(resultSet.getString("email"));
                        user.setRealName(resultSet.getString("real_name"));
                        user.setUserName(resultSet.getString("user_name"));
                        return user;
                    }
                });
        System.out.println(list);
    }

    @Test
    public void queryFofObject(){
       User u = template.queryForObject("select * from users where id = 1", User.class);
       System.out.println(u);
    }

    @Test
    public void groupTest(){
        List<Map<String,Object>> result = template.queryForList(
                "select category,parent from shop_category group by category,parent");
        System.out.println(result);
    }

}
