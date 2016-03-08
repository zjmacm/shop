package com.shop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import com.shop.dao.*;

public class BaseDaoImpl implements BaseDao {

	private final static String COUNT_SQL = "SELECT COUNT(0) FROM (${SQL}) TEMP_C";

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return this.namedParameterJdbcTemplate;
	}

	public void create(String tbName, Map<String, ?> row) {
		Assert.notEmpty(row);

		List<Object> plist = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("INSERT INTO ");
		// sb.append(tbName.toUpperCase()).append(" (");
		sb.append(tbName.toLowerCase()).append(" (");
		StringBuffer sb2 = new StringBuffer("VALUES (");
		for (String key : row.keySet()) {
			// sb.append(key.toUpperCase());
			sb.append(key.toLowerCase());
			Object o = row.get(key);
			if (o instanceof InnerSql) {
				sb2.append(((InnerSql) o).getSql());
			} else {
				sb2.append('?');
				plist.add(o);
			}
			sb.append(", ");
			sb2.append(", ");
		}
		/**
		 * 减去最后一个", "
		 */
		sb.replace(sb.length() - 2, sb.length() - 1, ")");
		sb2.replace(sb2.length() - 2, sb2.length() - 1, ")");
		sb.append(sb2);
		this.jdbcTemplate.update(sb.toString(), plist.toArray());
	}

	public int update(String tbName, Map<String, ?> row, Map<String, ?> conds) {
		Assert.notEmpty(row);
		Assert.notEmpty(conds);

		List<Object> plist = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("UPDATE ");
		// sb.append(tbName.toUpperCase()).append(" SET ");
		/**
		 * tbName 数据库表名
		 */
		sb.append(tbName.toLowerCase()).append(" SET ");

		for (String key : row.keySet()) {
			// sb.append(key.toUpperCase()).append('=');
			sb.append(key.toLowerCase()).append('=');
			Object o = row.get(key);
			if (o instanceof InnerSql) {
				sb.append(((InnerSql) o).getSql());
			} else {
				sb.append('?');
				plist.add(o);
			}
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length() - 1);
		sb.append(" WHERE ");
		for (String key : conds.keySet()) {
			// sb.append(key.toUpperCase()).append('=');
			sb.append(key.toLowerCase()).append('=');
			Object o = conds.get(key);
			if (o instanceof InnerSql) {
				sb.append(((InnerSql) o).getSql());
			} else {
				sb.append('?');
				plist.add(o);
			}
			sb.append(" AND ");
		}
		sb.delete(sb.length() - 5, sb.length() - 1);
		return this.jdbcTemplate.update(sb.toString(), plist.toArray());
	}

	public Map<String, Object> retrieve(String tbName, Map<String, ?> conds) {
		Assert.notEmpty(conds);

		List<Object> plist = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		// sb.append(tbName.toUpperCase()).append(" WHERE ");
		sb.append(tbName.toLowerCase()).append(" WHERE ");

		for (String key : conds.keySet()) {
			// sb.append(key.toUpperCase()).append('=');
			sb.append(key.toLowerCase()).append('=');
			Object o = conds.get(key);
			if (o instanceof InnerSql) {
				sb.append(((InnerSql) o).getSql());
			} else {
				sb.append('?');
				plist.add(o);
			}
			sb.append(" AND ");
		}
		/**
		减去最后一个and
		 */
		sb.delete(sb.length() - 5, sb.length() - 1);

		List<Map<String, Object>> list = queryForList(sb.toString(), plist.toArray());

		if (list.size() > 0) {
			return list.get(0);
		}
		return Collections.emptyMap();
	}

	public int delete(String tbName, Map<String, ?> conds) {
		Assert.notEmpty(conds);
		List<Object> plist = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("DELETE FROM ");
		// sb.append(tbName.toUpperCase()).append(" WHERE ");
		sb.append(tbName.toLowerCase()).append(" WHERE ");

		for (String key : conds.keySet()) {
			// sb.append(key.toUpperCase()).append('=');
			sb.append(key.toLowerCase()).append('=');
			Object o = conds.get(key);
			if (o instanceof InnerSql) {
				sb.append(((InnerSql) o).getSql());
			} else {
				sb.append('?');
				plist.add(o);
			}
			sb.append(" AND ");
		}
		sb.delete(sb.length() - 5, sb.length() - 1);

		return this.jdbcTemplate.update(sb.toString(), plist.toArray());
	}

	public List<Map<String, Object>> listAll(String tbName) {
		StringBuffer sb = new StringBuffer("SELECT * FROM ").append(tbName.toLowerCase());
		return queryForList(sb.toString());
	}

	public List<Map<String, Object>> listAll(String tbName, Order o) {
		StringBuffer sb = new StringBuffer("SELECT * FROM ")
		// .append(tbName.toUpperCase()).append(o.toSqlString());
				.append(tbName.toLowerCase()).append(o.toSqlString());
		return queryForList(sb.toString());
	}

	@SuppressWarnings("unchecked")
	public long count(String sql) {
		// System.out.println(COUNT_SQL.replaceFirst("\\$\\{SQL\\}", sql));

		return this.jdbcTemplate.queryForLong(COUNT_SQL.replaceFirst("\\$\\{SQL\\}", sql));
	}

	@SuppressWarnings("unchecked")
	public long count(String sql, Object... params) {
		if (params.length == 0) {
			return this.jdbcTemplate.queryForLong(COUNT_SQL.replaceFirst("\\$\\{SQL\\}", sql));
		}
		return this.jdbcTemplate.queryForLong(COUNT_SQL.replaceFirst("\\$\\{SQL\\}", sql), params);
	}

	public long count(String sql, Map<String, ?> params) {
		return this.namedParameterJdbcTemplate.queryForLong(COUNT_SQL.replaceFirst("\\$\\{SQL\\}", sql), params);
	}

	public List<Map<String, Object>> queryForList(String sql, Map<String, ?> params) {
		return this.namedParameterJdbcTemplate.query(sql, params, new MapRowMapper());
	}

	public List<Map<String, Object>> queryForList(String sql, Object... params) {
		if (params.length == 0) {
			return jdbcTemplate.query(sql, new MapRowMapper());
		}
		return jdbcTemplate.query(sql, params, new MapRowMapper());
	}

	public int executeUpdate(String sql, Object... params) {
		if (params.length == 0) {
			return this.jdbcTemplate.update(sql);
		}
		return this.jdbcTemplate.update(sql, params);
	}

	public int executeUpdate(String sql, Map<String, ?> params) {
		return this.namedParameterJdbcTemplate.update(sql, params);
	}

	public int[] batchUpdate(String[] sql) {
		return this.jdbcTemplate.batchUpdate(sql);
	}

	@SuppressWarnings("unchecked")
	public int[] batchUpdate(String sql, List<Map<String, ?>> batchValues) {
		return this.namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
	}

	public int[] batchUpdate(String sql, final Object[][] batchValues) {

		return this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				for (int index = 0; index < batchValues[i].length; index++) {
					Object value = batchValues[i][index];
					StatementCreatorUtils.setParameterValue(ps, index + 1, SqlTypeValue.TYPE_UNKNOWN, value);
				}
			}

			public int getBatchSize() {
				return batchValues.length;
			}
		});
	}

}
