package com.shop.service;

import com.shop.dao.Page;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * Created by yj on 14-12-11.
 * 搜索功能业务逻辑接口
 */
public interface SearchService {

    /**
     * 简单商品搜索
     * 根据搜索输入条件返回搜索结果
     * 用于主搜索框
     * @param text 搜索条件
     * @param page 页号
     * @return page 分页数据和分页信息
     * */
    public Page simpleGoodsSearch(String text, int page) throws IOException, ParseException;
}
