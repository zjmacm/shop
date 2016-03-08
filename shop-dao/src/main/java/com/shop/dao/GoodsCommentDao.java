package com.shop.dao;

import com.shop.domain.GoodsComment;

import java.util.Map;

/**
 * Created by ldz on 08/12/14.
 */
public interface GoodsCommentDao {
    /**
     *根据商品的id分页返回对应的评论
     * @param goodId the good's id
     * @param pageIndex the index of page,start from 0
     * @param pageSize the size of per page
     * @return the page
     */
//    PageJdbc<GoodsComment> getCommentsByGoodId(String goodId,int pageIndex,int pageSize);

    PageJdbc<Map<String,Object>> getCommentsByGoodId(String goodId,int pageIndex,int pageSize);

    /**
     * 对商品发表评论
     * @param goodsComment the comment to be created
     * @return the uuid of this comment
     */
    String create(GoodsComment goodsComment);

    /**
     * update the score of a comment
     * @param newGoodComment the new comment to replavce the old one
     */
    void update(GoodsComment newGoodComment);

    /**
     * remove a  comment by it's id
     * @param uuid the comment's id to be removed
     */
    void removeByUUID(String uuid);

    /**
     * remove a comment
     * @param goodsComment the comment to be removed
     */
    void remove(GoodsComment goodsComment);
}
