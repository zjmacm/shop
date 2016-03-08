package com.shop.dao.impl;

import com.shop.dao.CommonDao;
import com.shop.domain.Goods;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-12-6.
 * 通用数据库操作实现类
 */
@Repository("CommonDao")
public class CommonDaoImpl extends JdbcDaoImpl implements CommonDao{

    private static final String SEARCH = "select g.id,g.name,g.price,g.description,c.category " +
            "from shop_goods as g join shop_category as c " +
            "where g.category_id = c.id " +
            "and  g.name like ";

    @Override
    public List<Map<String,Object>> searchByName(String text) {
        String names = text.trim();
        String[] items = names.split(" ");
        String name = "\'%" + items[0] + "%\'";
        List<Map<String,Object>> result = getTemplate().queryForList(SEARCH + name);
        return result;
    }

    @Override
    public List<Goods> filterByCategory(String category, List<Map<String,Object>> list) throws InvocationTargetException, IllegalAccessException {
        ArrayList<Goods> result = new ArrayList<Goods>();
        for(int i = 0; i < list.size(); i++){
            String cate = (String) list.get(i).get("category");
            if(cate != null && cate.equals(category)){
                Goods goods = new Goods();
                try {
                    BeanUtils.populate(goods,list.get(i));
                    result.add(goods);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
