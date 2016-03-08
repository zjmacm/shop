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
@Table(name = "shop_goods_answer")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class GoodsAnswer extends BaseDomain {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "systemUUID")
    private String id;
    @Column(name = "question_id")
    private String questionId;
    @Column(name = "ans_comment")
    private String ansComment;
    @Column(name = "create_time")
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnsComment() {
        return ansComment;
    }

    public void setAnsComment(String ansComment) {
        this.ansComment = ansComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}