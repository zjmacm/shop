package com.shop.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import com.shop.utils.Tool;
import org.springframework.jdbc.core.RowMapper;

/**
 * domain 领域（域名）
 * @param <T>
 */

public class DomainWrap<T> implements RowMapper<T>{


	private T domain;

	private Class<T> domainClass;
	
	private DomainMetaData dmd;
	
	private static Map<Class<?>, DomainMetaData> domains;
	
	static {
		domains = new Hashtable<Class<?>, DomainMetaData>();
	}
	
	

	public static DomainMetaData getDomainMetaData(Class<?> c){
		
		DomainMetaData dmd = null;
		if(domains.containsKey(c)){
			dmd = domains.get(c);
		}else{
			dmd = new DomainMetaData(c);
			domains.put(c, dmd);
		}
		return dmd;
	}
	
	private T getDomain(){
		if(domain == null){
			try {
				return this.domainClass.newInstance();
			} catch (InstantiationException e) {
				throw new DaoException(e);
			} catch (IllegalAccessException e) {
				throw new DaoException(e);
			}
		}else{
			return domain;
		}
	}
	

	@SuppressWarnings("unchecked")
	public DomainWrap(T o) {
		this.domain = o;
		this.domainClass = (Class<T>) o.getClass();
		this.dmd = getDomainMetaData(this.domainClass);
	}
	

	public DomainWrap(Class<T> t){
		this.domainClass = t;
		this.domain = null;
		this.dmd = getDomainMetaData(t);
	}
	
	
	public String getTableName(){
		return dmd.getTableName();
	}

	public Map<String, Object> getRowMap(){
		T t = getDomain();
		Map<String, Object> row = new HashMap<String, Object>();
		Map<String, Field> columns = dmd.getColumns();
		Set<String> columnsWithoutPk = dmd.getolumnsWithoutPk();
		try {
			for(String columnName : columnsWithoutPk){
				Field f = columns.get(columnName);
				row.put(columnName, f.get(t));
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		return row;
	}
	

	public Map<String, Object> getRowMapIgnoreNull(){
		T t = getDomain();
		Map<String, Object> row = new HashMap<String, Object>();
		Map<String, Field> columns = dmd.getColumns();
		Set<String> columnsWithoutPk = dmd.getolumnsWithoutPk();
		try {
			for(String columnName : columnsWithoutPk){
				Field f = columns.get(columnName);
				Object fo = f.get(t);
				if(null != fo)
					row.put(columnName, fo);
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		return row;
	}
	
	
	public Map<String, Object> getIdMap(){
		T t = getDomain();
		Map<String, Object> idmap = new HashMap<String, Object>();
		Map<String, Field> columns = dmd.getColumns();
		Map<String, IdGenerationType> pks = dmd.getPks();
		try {
			for(String columnName : pks.keySet()){
				Field f = columns.get(columnName);
				idmap.put(columnName, f.get(t));
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		return idmap;
	}
	
	
	public Map<String, Object> getRowMapForCreate(){
		T t = getDomain();
		Map<String, Object> row = new HashMap<String, Object>();
		Map<String, Field> columns = dmd.getColumns();
		Set<String> columnsWithoutPk = dmd.getolumnsWithoutPk();
		Map<String, IdGenerationType> pks = dmd.getPks();
		try {
			for(String columnName : columnsWithoutPk){
				Field f = columns.get(columnName);
				row.put(columnName, f.get(t));
			}
			for(String columnName : pks.keySet()){
				Field f = columns.get(columnName);
				IdGenerationType idtype = pks.get(columnName);
				Object value = f.get(t);
				switch(idtype){
					case AUTO:
						break;
					case ASSIGN:
						row.put(columnName, value);
						break;
					case SEQUENCE:
						//TODO: doit
						break;
					case UUID:
						if(null == value){
							String uuid = Tool.getUUID32();
							f.set(t, uuid);
							row.put(columnName, uuid);
						}
				}
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		return row;
	}
	
	public T mapRow(ResultSet rs, int index) throws SQLException {
		Map<String, Field> columns = dmd.getColumns();
		T t = getDomain();
		try {
			for(String columnName : columns.keySet()){
				Field f = columns.get(columnName);
				Class<?> ftype = f.getType();
				if(String.class.equals(ftype)){
						f.set(t, rs.getString(columnName));
				}else if ("int".equals(ftype.getName())){
					f.setInt(t, rs.getInt(columnName));
				}else if(Integer.class.equals(ftype)) {
					f.set(t, new Integer(rs.getInt(columnName)));
	            }else if("long".equals(ftype.getName()) ) {
	            	f.setLong(t, rs.getLong(columnName));
	            }else if(Long.class.equals(ftype)) {
	            	f.set(t, new Long(rs.getLong(columnName)));
	            }else if(Date.class.equals(ftype)){
	            	f.set(t, rs.getTimestamp(columnName));//会否有问题？
	            }else if(Timestamp.class.equals(ftype)){
	            	f.set(t, rs.getTimestamp(columnName));
	            }else if("double".equals(ftype.getName()) ) {
	            	f.setDouble(t, rs.getDouble(columnName));
	            }else if( Double.class.equals(ftype)) {
	            	f.set(t, new Double(rs.getDouble(columnName)));
	            }else if("short".equals(ftype.getName()) ) {
	            	f.setShort(t, rs.getShort(columnName));
	            }else if( Short.class.equals(ftype)) {
	            	f.set(t, new Short(rs.getShort(columnName)));
	            }else if("float".equals(ftype.getName())) {
	            	f.setFloat(t, rs.getFloat(columnName));
	            }else if( Float.class.equals(ftype)) {
	            	f.set(t, new Float(rs.getFloat(columnName)));
	            }
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		
		return t;
	}
	
}
