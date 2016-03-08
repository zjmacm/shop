package com.shop.dao.impl;

import com.shop.dao.ArticleDao;
import com.shop.dao.Page;
import com.shop.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldz on 04/12/14.
 */
@Repository("articleDao")
public class ArticleDaoImpl extends HibernateDao implements ArticleDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  DataSource dataSource;
    @Override
    public Article getArticleById(String id) {
        return get(Article.class,id);
    }


    /**
     * 先就简单模糊查粑粑
     * 以后想用 lucene实现
     * @param content
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page getArticleByContent(String content,int pageNo, int pageSize) {
        final String QUERY_BY_CONTENT = "select * from shop_article where content like ?";

        return pagedQuery(QUERY_BY_CONTENT,pageNo,pageSize,"%" + content + "%");
    }

    @Override
    public String createArticle(Article article) {
        return save(article);
    }

    @Override
    public void update(Article article) {
        update(article);

    }

    @Override
    public void remove(Article article) {
        remove(article);
    }

    @Override
    public Page getArticleByTitle(String title,int pageNo, int pageSize) {
        final String QUERY_BY_TITLE = "select * from shop_article where title like ?";

        return pagedQuery(QUERY_BY_TITLE,pageNo,pageSize,"%" + title + "%");
    }

    @Override
    public List<Article> getArticleByKeyWord(String keyWord,int pageNo, int pageSize) {
        List<Article> result = new ArrayList<>();
        String []keys = keyWord.split(",");
        for(String key:keys) {
            final String QUERY_BY_KEYWORD = "select * from shop_article where key_word like ?";
            Page tmpPage =pagedQuery(QUERY_BY_KEYWORD,pageNo,pageSize,pageNo,"%"+key+"%");
            List<Article> tmp = tmpPage.getResult();
            result.addAll(tmp);
        }
        return result;
    }

    /**
     * JDBC查询封装实体
     *
     * @see org.springframework.jdbc.object.MappingSqlQuery
     * */
    private class ArticleMappingQuery extends MappingSqlQuery<Article> {

        /**
         * @param ds          注入数据源
         * @param sql         查询语句
         * @param queryColumn    筛选列名 如按id查询则传入"id"
         * @param columnType  筛选列名的类型 javax.sql.Types内置类型(int)
         */
        public ArticleMappingQuery(DataSource ds, String sql,
                                   String queryColumn, int columnType) {
            super(ds, sql);
            super.declareParameter(new SqlParameter(queryColumn, columnType));
            compile();
        }

        public ArticleMappingQuery(DataSource ds, String sql) {
            super(ds, sql);
            compile();
        }

        @Override
        protected Article mapRow(ResultSet resultSet, int i) throws SQLException {
            Article article = new Article();
            article.setId(resultSet.getString("id"));
            article.setKeyWord(resultSet.getString("key_word"));
            article.setTitle(resultSet.getString("title"));
            article.setContent(resultSet.getString("content"));
            article.setLastUpdate(resultSet.getDate("last_update"));
            article.setCreateTime(resultSet.getDate("create_time"));
            return article;
        }
    }

    }
