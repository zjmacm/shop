package com.shop.dao;

import com.shop.domain.Category;

import java.util.List;
import java.util.Map;

/**
 * Created by yj on 14-11-26.
 *
 * 商品分类表操作接口
 */
public interface CategoryDao {

    public String create(Category category);

    public void update(Category category);

    public void remove(Category category);

    /*获取所有分类信息
    * map中包含category和parent*/
    public List<Map<String,Object>> getAllCategory();

    public Category getCategoryById(String id);

    public Category getCategoryByName(String category);

    /**
     * 获取分类的子节点
     * @param parent  父分类
     * @return
     */
    public List<Map<String,Object>> getChildByCategory(Category parent);

    /**
     * 获取所有一级分类
     * @return
     */
    public List<Map<String,Object>>getAllParentCategory();

}
