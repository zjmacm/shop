
package com.shop.dao;


public class InnerSql {
	
	
	private String sql = "";


	public InnerSql(String sql){
		this.sql = sql;
	}
	

	public String getSql(){
		return sql;
	}
	
}
