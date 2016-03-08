package com.shop.dao;

import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;


public class MapRowMapper extends ColumnMapRowMapper {

	@Override
	protected Map<String, Object> createColumnMap(int columnCount) {
		return new LowerCaseHashMap<String, Object>(columnCount);
		//return new CaseInsensitiveHashMap<String, Object>(columnCount);
	}
}
