package com.shop.dao;

import java.lang.reflect.Field;
import java.util.*;

public class DomainMetaData {

	private String tableName;

	private Map<String, IdGenerationType> pks;

	private Map<String, Field> columns;

	private Set<String> columnsWithoutPk;

	public String getTableName() {
		return tableName;
	}

	public Map<String, IdGenerationType> getPks() {
		return pks;
	}

	public Map<String, Field> getColumns() {
		return columns;
	}

	public Set<String> getolumnsWithoutPk() {
		return columnsWithoutPk;
	}

	public DomainMetaData(Class<?> c) {
		Map<String, IdGenerationType> pks0 = new HashMap<String, IdGenerationType>();
		Map<String, Field> columns0 = new HashMap<String, Field>();
		Set<String> columnsWithoutPk0 = new HashSet<String>();

		Table table = c.getAnnotation(Table.class);
		if (table == null) {
			throw new DaoException("ORM ERROR. no @Table found in class:" + c.getName());
		}

		String tname = table.value();

		if (isEmpty(tname)) {
			tname = c.getSimpleName();
		}
		this.tableName = tname;

		Field[] fs = c.getDeclaredFields();
		for (Field f : fs) {
			Column col = f.getAnnotation(Column.class);
			if (null == col) {
				continue;
			}
			String columnName = col.value();

			if (isEmpty(columnName)) {
				columnName = f.getName();
			}

			Pk pk = f.getAnnotation(Pk.class);
			if (null != pk) {
				IdGenerationType idType = pk.value();
				pks0.put(columnName, idType);

			} else {
				columnsWithoutPk0.add(columnName);
			}
			f.setAccessible(true);
			columns0.put(columnName, f);
		}

		this.pks = Collections.unmodifiableMap(pks0);
		this.columns = Collections.unmodifiableMap(columns0);
		this.columnsWithoutPk = Collections.unmodifiableSet(columnsWithoutPk0);
	}

	private static boolean isEmpty(String s) {
		return "".equals(s);
	}

}
