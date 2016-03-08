package com.shop.service;


import com.shop.domain.Category;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-26.
 *
 * 商品分类业务逻辑接口
 */
public interface CategoryService {

    public String create(Category category);

    public void update(Category category);

    public void remove(Category category);

    public void removeById(String id);

    public void removeByName(String name);

    public Category getById(String id);

    public Category getByName(String name);

    public Map<String, com.shop.service.impl.CategoryServiceImpl.Hierarchy> getAll();

    /**
     * 获取分类下的子节点
     * @param parent
     * @return
     */
    public List<Category>getChildByCategory(Category parent);

    /**
     * 获取所有一级分类
     * @return
     */
    public List<Category>getAllParentCategory();
}
