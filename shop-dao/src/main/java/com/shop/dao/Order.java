package com.shop.dao;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private boolean positive;

	private List<OrderFragment> orderList = null;

	private class OrderFragment {

		String columnName;

		boolean ascending;

		OrderFragment(String colName, boolean asc) {
			this.columnName = colName;
			this.ascending = asc;
		}

		String toSqlString() {
			return new StringBuffer(this.columnName).append((positive && ascending) ? " asc" : " desc").toString();
		}
	}

	public Order() {
		this.positive = true;
		this.orderList = new ArrayList<OrderFragment>();
	}

	public Order reverse() {
		this.positive = !this.positive;
		return this;
	}

	public Order desc(String columnName) {
		orderList.add(new OrderFragment(columnName, false));
		return this;

	}

	public Order asc(String columnName) {
		orderList.add(new OrderFragment(columnName, true));
		return this;
	}

	public String toSqlString() {
		if (orderList.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (OrderFragment rf : orderList) {
			sb.append(", ").append(rf.toSqlString());
		}
		if (sb.length() > 0)
			return " ORDER BY " + sb.substring(2);
		return "";
	}

	public String toString() {
		return toSqlString();
	}
}
