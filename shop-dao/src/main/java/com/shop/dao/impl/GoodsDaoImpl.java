package com.shop.dao.impl;

import com.shop.dao.*;
import com.shop.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-28.
 * 商品信息数据库操作实现类
 */
@Repository("GoodsDao")
public class GoodsDaoImpl extends HibernateDao implements GoodsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RedisDao redisDao;

    @Autowired
    private SimpleDaoJDBC simpleDaoJDBC;

    private final static String GET_GOODS_BY_CATEGORY = "select id,name,price,description" +
            ",photo from shop_goods where category_id = ? limit ?,?";

    private final static String ORDER_BY_SCAN_TIME = "select id,name,price,description" +
            ",photo,scan_time from shop_goods order by scan_time desc limit 0,?";

    private final static String ORIGIN_SQL = "select ${*} from shop_goods join shop_category " +
            "on shop_goods.category_id = shop_category.id";
    @Override
    public String create(Goods goods) {
        String id = save(goods);
        goods.setId(id);
        //将最新上传的商品信息缓存到latest-list
        redisDao.maintainListAdd(
                DBConstant.LATEST_GOODS_LIST,
                goods,
                DBConstant.LATEST_GOODS_LENGTH
        );
        return id;
    }


    public void update(Goods goods){
        super.update(goods);
    }

    @Override
    public Goods getById(String id) {
        return get(Goods.class, id);
    }

    /*直接从缓存中取*/
    @Override
    public List<Goods> getLatest() {
        return redisDao.listGetAll(DBConstant.LATEST_GOODS_LIST);
    }

    //推荐算法:返回浏览次数最多的几个商品
    @Override
    public List<Goods> getRecommondation(int number) {
        List<Goods> result = jdbcTemplate.query(
                ORDER_BY_SCAN_TIME,
                new Object[]{DBConstant.DEFAULT_PAGE_SIZE},
                new Mapper());
        return result;
    }

    /**
     * 根据分类获取分页后的商品数据
     * @param categoryId 分类id
     * @param page 页号 从1开始
     * */
    @Override
    public List<Goods> getGoodsByCategory(String categoryId, int page) {
        if(page < 1)
            throw new IllegalArgumentException("page number cannot less than 1");
        List<Goods> result =  jdbcTemplate.query(
                GET_GOODS_BY_CATEGORY,
                new Object[]{
                        categoryId,
                        DBConstant.DEFAULT_PAGE_SIZE * (page - 1),
                        DBConstant.DEFAULT_PAGE_SIZE},
                new Mapper()
                );
        return result;
    }

    @Override
    public List<Map<String, Object>> getAllGoods(String[] fields) {
        final String sql = buildSql(fields);
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }

    //填充指定字段
    private String buildSql(String[] fields){
        StringBuilder builder = new StringBuilder();
        for(String str : fields){
            builder.append(str + ",");
        }
        builder.deleteCharAt(builder.length() - 1);//去掉最后一个逗号
        return ORIGIN_SQL.replaceFirst("\\$\\{\\*\\}",builder.toString());
    }

    private class Mapper implements RowMapper <Goods>{

        @Override
        public Goods mapRow(ResultSet resultSet, int i) throws SQLException {
            com.shop.domain.Goods goods = new com.shop.domain.Goods();
            goods.setId(resultSet.getString("id"));
            goods.setName(resultSet.getString("name"));
            goods.setPrice(resultSet.getDouble("price"));
            goods.setDescription(resultSet.getString("description"));
            goods.setPhoto(resultSet.getString("photo"));
            goods.setScanTime(resultSet.getInt("scan_time"));
            return goods;
        }
    }

    @Override
    public PageJdbc<Map<String, Object>> pageQueryGoods(String conds, int pageIndex, int pageSize) {
        final String sql = "select * from shop_goods where "+ conds +"="+conds;
        Map<String,Object> params = new HashMap<>();
        params.put(conds,conds);
        Order order = new Order().desc("create_time");
        return simpleDaoJDBC.pageQuery(sql,params,pageIndex,pageSize,order);
    }
}
