package com.shop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shop.dao.*;

public class MySQLSimpleDaoImpl extends AbstractSimpleDao {

	private static final String SPLIT_PAGE_SQL = "select * from (${SQL}) as TEMP_PGS limit :__offset , :__limit";

	private Map<String, Object> makeConditions(Map<String, ?> params, int pageindex, int pagesize) {

		if (params.containsKey("__limit") || params.containsKey("__offset")) {
			throw new IllegalArgumentException("common defined param name can not be cover![__limit or __offset].");
		}
		if(pageindex <= 0 || pagesize <= 0){ throw new
		 IllegalArgumentException
		 ("pageindex and pagesize must be positive integer.");
        }


		Map<String, Object> p = new HashMap<String, Object>();
		p.putAll(params);
		int offset = (pageindex - 1) * pagesize;

		p.put("__limit", pagesize);
		p.put("__offset", offset);

		return p;
	}

	public <T> PageJdbc<T> pageQuery(String sql, Map<String, ?> params, int pageindex, int pagesize, Order order, Class<T> c) {

		DomainWrap<T> dw = new DomainWrap<T>(c);
		Map<String, Object> p = makeConditions(params, pageindex, pagesize);

		long totalCount = count(sql, params);

		sql += order.toSqlString();
		String execSql = SPLIT_PAGE_SQL.replaceAll("\\$\\{SQL\\}", sql);
		List<T> pageList = getNamedParameterJdbcTemplate().query(execSql, p, dw);

        PageJdbc<T> page = new PageJdbc<T>();
		// page.setSize(pagesize);
		// page.setLimit(pagesize);
		// page.setIndex(pageindex);
		// page.setStart(pageindex-1);
		page.setTotal(totalCount);
		page.setList(pageList);

		return page;
	}

	public PageJdbc<Map<String, Object>> pageQuery(String sql, Map<String, ?> params, int pageindex, int pagesize,
			Order order) {

		Map<String, Object> p = makeConditions(params, pageindex, pagesize);

		long totalCount = count(sql, params);

		sql += order.toSqlString();

		String execSql = SPLIT_PAGE_SQL.replaceAll("\\$\\{SQL\\}", sql);
		List<Map<String, Object>> pageList = queryForList(execSql, p);

        PageJdbc<Map<String, Object>> page = new PageJdbc<Map<String, Object>>();

		page.setTotal(totalCount);
		if (params.get("page") != null) {
			page.setPage(Integer.parseInt(params.get("page").toString()));
		} else {
			page.setPage(1);
		}

		page.setList(pageList);

		// page.setPage(pageindex);

		return page;
	}

}
