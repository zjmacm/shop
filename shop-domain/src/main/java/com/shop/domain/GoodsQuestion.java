package com.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ldz on 08/12/14.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_goods_question")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class GoodsQuestion extends BaseDomain {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "systemUUID")
    private String id;
    @Column(name = "goods_id")
    private String goodsId;
    @Column(name = "ask_comment")
    private String askComment;
    @Column(name = "user_id")
    private String userId;
    @Column(name ="question_times")
    private Integer questionTimes;  //此问题询问次数
    @Column(name ="status")
    private String status;
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

    public String getAskComment() {
        return askComment;
    }

    public void setAskComment(String askComment) {
        this.askComment = askComment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuestionTimes() {
        return questionTimes;
    }

    public void setQuestionTimes(Integer questionTimes) {
        this.questionTimes = questionTimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}