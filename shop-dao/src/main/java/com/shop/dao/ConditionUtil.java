package com.shop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConditionUtil {

	public static final String AND = " AND ";

	public static final String OR = " OR ";

	public static String where(String s) {
		if (!isEmpty(s)) {
			return " WHERE " + s;
		}
		return "";
	}

	public static String like(String s) {
		return like(s, "%?%");
	}

	public static String like(String s, String pattern) {
		if (isEmpty(s))
			return s;
		return pattern.replaceAll("\\?", s);
	}

	public static String and(Map<String, String> map, String[][] condArr) {
		if (condArr == null || map == null || map.size() == 0)
			return "";
		return joinArray(conds(map, condArr), AND);
	}

	public static String or(Map<String, String> map, String[][] condArr) {
		if (condArr == null || map == null || map.size() == 0)
			return "";
		return joinArray(conds(map, condArr), OR);
	}

	private static String makeNamedParameterSql(String op, String sqlParamName, String namedParamName) {
		StringBuffer sb = new StringBuffer();
		sb.append(sqlParamName).append(' ').append(op).append(" :").append(namedParamName);
		return sb.toString();
	}

	private static boolean isEmpty(String s) {
		return null == s || "".equals(s);
	}

	private static List<String> conds(Map<String, String> map, String[][] condArr) {
		List<String> list = new ArrayList<String>();
		if (condArr != null && map != null) {
			for (String[] c : condArr) {
				if (c != null && c.length == 3 && !isEmpty(map.get(c[2]))) {
					String cond = makeNamedParameterSql(c[0], c[1], c[2]);
					if (cond.length() > 0)
						list.add(cond);
				}
			}
		}
		return list;
	}

	private static String joinArray(List<?> list, String s) {
		if (list == null)
			return null;
		if (list.size() == 0)
			return "";
		String ss = "";
		for (int i = 0; i < list.size(); i++) {
			ss += s + list.get(i);
		}
		if (ss.length() > 0) {
			ss = ss.substring(s.length());
		}
		return ss;
	}

}
