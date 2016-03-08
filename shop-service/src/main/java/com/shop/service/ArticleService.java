package com.shop.service;

import com.shop.dao.Page;
import com.shop.domain.Article;

import java.util.HashMap;
import java.util.*;

/**
 *
 * Created by ldz on 04/12/14.
 */
public interface ArticleService {
    /**
     * 增加文章
     * @param article
     * @return
     */
    String createArticle(Article article);

    void update(Article article);
    void remove(Article article);

    /**
     * 根据文章id获取文章
     * @param id
     * @return
     */
    Article getArticleById(String id);

    /**
     * 根据文章标题获取文章，可能会是分页
     * @param title
     * @return
     */
    Page getArticleByTitle(String title,int pageNo, int pageSize);

    /**
     * 根据关键字检错文章
     * @param KeyWord
     * @return
     */

    List<Article> getArticleByKeyWord(String KeyWord,int pageNo, int pageSize);

    /**
     * 根据文章的部分内容获取文章
     * @param content
     * @return
     */
    Page getArticleByContent(String content,int pageNo, int pageSize);

}
