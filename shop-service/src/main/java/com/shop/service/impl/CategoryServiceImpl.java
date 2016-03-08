package com.shop.service.impl;

import com.shop.dao.CategoryDao;
import com.shop.domain.Category;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by yj on 14-11-26.
 *
 * 商品分类业务逻辑实现类
 */
@Transactional(readOnly = true)
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;


    //dao层实现类抛出的异常不会被捕获,直接在事务中回滚
    @Override
    public String create(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void remove(Category category) {
        categoryDao.remove(category);
    }

    @Override
    public void removeById(String id) {
        if(null == id || id.equals(""))
            throw new IllegalArgumentException("id cannot be null or empty");
        Category category = new Category();//种类
        category.setId(id);
        categoryDao.remove(category);
    }

    @Override
    public void removeByName(String name) {
        if(null == name || name.equals(""))
            throw new IllegalArgumentException("name cannot be null or empty");
        Category category = new Category();
        category.setCategory(name);
        categoryDao.remove(category);
    }

    @Override
    public Category getById(String id)  { return categoryDao.getCategoryById(id);}

    @Override
    public Category getByName(String name) {
        return categoryDao.getCategoryByName(name);
    }

  
    

    /**
     * 从下层获取所有分类并将所有分类层次化
     只适用于二级分类
     * @return
     */
    @Override
    public Map<String,Hierarchy> getAll() {
        List<Map<String,Object>> allCate = categoryDao.getAllCategory();
        System.out.println(allCate);
        Map<String,Hierarchy> result = new HashMap<String,Hierarchy>();
        //建立一级分类
        for(Map<String,Object> tmp :allCate) {
            if (null == tmp.get("parent_id")) {
                Category category = Map2Category(tmp);
                Hierarchy root = new Hierarchy(category);
                result.put(root.getName(), root);
            }
        }
            //剩下都是二级分类了
            for(Map<String,Object> tmpNew :allCate){

                String parentId = (String) tmpNew.get("parent_id");
                if(parentId==null)
                    continue;
                for(String name:result.keySet()){
                    Hierarchy hierarchy = (Hierarchy)result.get(name);

                    if(hierarchy.getId().equals(parentId)){
                        Hierarchy child = new Hierarchy(Map2Category(tmpNew));

                        result.get(name).getChildren().add(child);


                    }
                }

            }

        return result;
    }



    @Override
    public List<Category> getChildByCategory(Category parent) {
        List<Map<String,Object>>tmp = categoryDao.getChildByCategory(parent);
        List<Category> result = new ArrayList<>();
        for(Map<String,Object> keyMap:tmp){
            Category category = new Category();
            if(keyMap.containsKey("id")){
                category.setId((String)keyMap.get("id"));
            }
            if(keyMap.containsKey("category")){
                category.setCategory((String) keyMap.get("category"));
            }
            if(keyMap.containsKey("parent_id")){
                category.setParentId((String) keyMap.get("parent_id"));
            }
            if(keyMap.containsKey("create_time")){
                category.setCreateTime((java.sql.Date)keyMap.get("create_time"));
            }
            result.add(category);
        }
        return result;
    }

    public class Hierarchy{//层级
        String name;
        String id ;
        List<Hierarchy> children;

        private Hierarchy(Category category) {
            this.name = category.getCategory();
            this.id = category.getId();
            children = new ArrayList<Hierarchy>();
        }

        public void setName(String name) {
            this.name = name;
        }
        public void setId(String id) {
            this.id = id;
        }
        public void setChildren(List<Hierarchy> children) {
            this.children = children;
        }

        public String getName() {
            return name;
        }
        public String getId(){
            return this.id;
        }

        public List<Hierarchy> getChildren() {
            return children;
        }

        @Override
        public String toString() {
            return "Hierarchy{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    /**
     * 从一个分类列表中寻找父分类
     * @param data
     * @return
     */
   private Category getCategory(List<Category> data,String parentId) {
       for (Category category : data) {
           if (category.getId() == parentId) {
               return category;
           }
       }
       return  null;
   }
    private Category Map2Category(Map<String,Object> data){
        Category category = new Category();
        if(data.containsKey("id")){
            category.setId((String)data.get("id"));
        }
        if(data.containsKey("category")){
            category.setCategory((String) data.get("category"));
        }
        /**if(data.containsKey("category")){
            category.setCategory(data.get("category").toString());
        }**/
        if(data.containsKey("parent_id")){
            category.setParentId((String) data.get("parent_id"));
        }
        if(data.containsKey("create_time")){
            category.setCreateTime(new Date(((Timestamp)data.get("create_time")).getTime()));
        }
        return category;
    }

    @Override
    public List<Category> getAllParentCategory() {
        List<Map<String,Object>>tmp = categoryDao.getAllParentCategory();
        List<Category> result = new ArrayList<>();
        for(Map<String,Object>category:tmp){
            result.add(Map2Category(category));
        }
        return result;
    }
}
