package com.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


/**
 * Created by yj on 14-11-27.
 * 商品实体类
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_goods")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class Goods extends BaseDomain{

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "systemUUID")
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    double price;//商品价格

    @Column(name = "description")
    String description;//商品规格

    @Column(name = "cost")
    double cost;//成本价

    @Column(name = "sale_price")
    double salePrice;//销售价

    @Column(name = "discount_price")
    double discountPrice;//优惠价

    @Column(name = "credit")
    int credit;

    @Column(name = "brand")
    String brand;

    @Column(name = "category_id")
    String categoryId;

    @Column(name = "photo")
    String photo;//图片路径

    @Column(name = "store_id")
    String storeId;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "audit")
    String audit;

    @Column(name = "create_time")
    Date createTime;

    @Column(name = "status")
    String status;

    @Column(name = "sale_quantity")
    int saleQuantity;

    @Column(name = "scan_time")
    int scanTime;

    @Column(name = "seller_id")
    String sellerId;

    @Column(name = "images")
    String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public int getScanTime() {
        return scanTime;
    }

    public void setScanTime(int scanTime) {
        this.scanTime = scanTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }
}

