package com.shop.dao;

/**
 * 数据库表常量类
 *将数据库表等常量定义在此处,以后sql语句时候就直接写常量了,这样以后方便改变就没有必要去改变所有的出现地方了
 *@author ldz
 */
public class DBConstant {

	/**
	 * 用户表
	 */
	public final static String SHOP_USER = "user";

    //保存最新上传商品信息的reids-list 列名
	public final static String LATEST_GOODS_LIST = "goods.latest";

    //保存最新上传商品信息的redis-list 长度
    public final static int LATEST_GOODS_LENGTH = 10;

    //默认分页大小
    public final static int DEFAULT_PAGE_SIZE = 10;

    public static final int SEARCH_PAGE_SIZE = 10;
}
