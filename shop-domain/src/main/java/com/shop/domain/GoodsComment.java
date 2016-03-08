package com.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品评论
 * Created by ldz on 08/12/14.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_goods_comment")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class GoodsComment extends BaseDomain {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "systemUUID")
    private String id;
    @Column(name = "goods_id")
    private String goodsId;
    @Column(name = "comment_score")
    private Integer commentScore;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}