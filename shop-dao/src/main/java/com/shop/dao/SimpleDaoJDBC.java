package com.shop.dao;

import java.util.List;
import java.util.Map;

public interface SimpleDaoJDBC extends BaseDaoJDBC {

	public <T> void create(T o);

	public <T> void retrieve(T o);

	public <T> int update(T o);

	public <T> int updateIgnoreNull(T o);

	public <T> int delete(T o);

	public <T> List<T> listAll(Class<T> c);

	public <T> List<T> listAll(Class<T> c, Order o);

	public <T> PageJdbc<T> pageQuery(Class<T> c, String condition, Map<String, ?> params, int pageindex, int pagesize,
                                 Order order);

	public <T> PageJdbc<T> pageQuery(Class<T> c, String condition, Map<String, ?> params, Order order);

	public <T> PageJdbc<T> pageQuery(String sql, Map<String, ?> params, int pageindex, int pagesize, Order order, Class<T> c);

	public <T> PageJdbc<T> pageQuery(String sql, Map<String, ?> params, Order order, Class<T> c);

	public PageJdbc<Map<String, Object>> pageQuery(String sql, Map<String, ?> params, int pageindex, int pagesize,
                                               Order order);

	public PageJdbc<Map<String, Object>> pageQuery(String sql, Map<String, ?> params, Order order);

}
