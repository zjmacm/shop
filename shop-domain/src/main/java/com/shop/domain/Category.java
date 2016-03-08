package com.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


/**
 * Created by yj on 14-11-26.
 * 商品分类实体类
 * */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_category")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class Category extends BaseDomain{

    @Id
    @GeneratedValue(generator="systemUUID")//使用32位uuid生成策略
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private String id;

    @Column(name = "parent_id")
    private String parentId;//父分类外键Id

    @Column(name = "category")
    private String category;

    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
