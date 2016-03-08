package com.shop.dao;

import com.shop.domain.Seller;

/**
 * Created by root on 14-12-3.
 */
public interface SellerDao {
    /**
     * 获取用户
     * @param id UUID
     * @return
     */
    Seller getSeller(String id);

    /**
     * 通过email获取用户
     * @param email
     * @return
     */
    Seller findSellerByEmail(String email);

    /**
     * 通过Sellername获取用户
     * @param sellerName
     * @return
     */
    Seller findSellerBySellerName(String sellerName);


    /**
     * 创建用户
     * @param seller
     * @return SellerID
     */
    String createSeller(Seller seller);

    /**
     * save Seller
     * @param entity
     * @return
     */
    String save(Object entity);

    /**
     * update a Seller
     * @param entity to be updated Seller
     */
    void update(Object entity);
}
