package com.shop.service.impl;

import com.shop.dao.ArticleDao;
import com.shop.dao.Page;
import com.shop.domain.Article;
import com.shop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by ldz on 04/12/14.
 */
@Transactional(readOnly = true)
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public String createArticle(Article article) {
        Assert.notNull(article,"acticle not null");
        Assert.hasText(article.getTitle(),"文章标题不能为空");
        Assert.hasText(article.getContent(),"文章内容不能是空");
        return articleDao.createArticle(article);
    }

    @Override
    public void update(Article article) {
        Assert.notNull(article,"acticle not null");
        Assert.hasText(article.getId(),"文章id不能是空");
        articleDao.update(article);

    }

    @Override
    public void remove(Article article) {
        Assert.notNull(article,"acticle not null");
        Assert.hasText(article.getId(),"文章id不能是空");
        articleDao.remove(article);
    }

    @Override
    public Article getArticleById(String id) {
        Assert.hasText(id,"id not null");
        return articleDao.getArticleById(id);
    }

    @Override
    public Page getArticleByTitle(String title,int pageNo, int pageSize) {
        Assert.hasText(title,"title not null");
        Assert.isTrue(pageNo>=0,"pageNo must >= 0");
        Assert.isTrue(pageSize>=0,"pageSize must >= 0");
        return articleDao.getArticleByTitle(title,pageNo,pageSize);
    }

    @Override
    public List<Article>getArticleByKeyWord(String keyWord,int pageNo, int pageSize) {
        Assert.hasText(keyWord,"title not null");
        Assert.isTrue(pageNo>=0,"pageNo must >= 0");
        Assert.isTrue(pageSize>=0,"pageSize must >= 0");
        return articleDao.getArticleByKeyWord(keyWord,pageNo,pageSize);
    }

    @Override
    public Page getArticleByContent(String content,int pageNo, int pageSize) {
        Assert.hasText(content,"title not null");
        Assert.isTrue(pageNo>=0,"pageNo must >= 0");
        Assert.isTrue(pageSize>=0,"pageSize must >= 0");
        return articleDao.getArticleByContent(content,pageNo,pageSize);
    }

}
