package com.shop.dao;

import java.util.*;

public interface BaseDaoJDBC {

	public void create(String tbName, Map<String, ?> row);
		
	public Map<String, Object> retrieve(String tbName, Map<String, ?> conds);
	
	public int update(String tbName, Map<String, ?> row, Map<String, ?> conds);
	
	public int delete(String tbName, Map<String, ?> conds);
	
	public List<Map<String, Object>> listAll(String tbName);
	
	public List<Map<String, Object>> listAll(String tbName, Order o);
	
	
	public long count(String sql);
	

	public long count(String sql, Object... params);
	
	
	public long count(String sql, Map<String, ?> params);
	

	public List<Map<String, Object>> queryForList(String sql, Map<String, ?> params);
	
	
	public List<Map<String, Object>> queryForList(String sql, Object... params);
	
	
	public int executeUpdate(String sql, Object... params);
	

	public int executeUpdate(String sql, Map<String, ?> params);
	

	public int[] batchUpdate(String[] sql);
	
	public int[] batchUpdate(String sql, List<Map<String, ?>> batchValues);
	
	
	public int[] batchUpdate(String sql, Object[][] batchValues);
	
}
