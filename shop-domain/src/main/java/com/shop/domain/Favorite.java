package com.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 收藏夹
 * Created by ldz on 09/12/14.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_favorite")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class Favorite extends BaseDomain {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "systemUUID")
    private String id;
    @Column(name = "user_id")
    private String user_id;

    @Column(name = "goods_id")
    private String goodsId;
    @Column(name = "quantity")
    private Integer quantity;


    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}