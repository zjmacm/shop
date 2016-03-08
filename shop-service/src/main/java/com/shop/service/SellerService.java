package com.shop.service;

import com.shop.domain.Seller;


/**
 * Created by root on 14-11-30.
 */

public interface SellerService {

    /**
     * 根据Id查找Seller
     * @param id
     * @return Seller对象
     */
    Seller getSeller(String id);

    /**
     * 根据email查找Seller
     * @param email
     * @return Seller对象
     */
    Seller findSellerByEmail(String email);

    /**
     * 根据SellerName查找Seller
     * @param SellerName
     * @return
     */
    Seller findSellerBySellerName(String SellerName);

    /**
     * 创建Seller记录
     * @param Seller
     * @return Seller主键
     */
    String createSeller(Seller Seller);

    /**
     * 用户（买家和卖家）注册
     * @param  Seller
     * @param flag buyer or saler
     * @return uuid
     */
    boolean register(Seller Seller,boolean flag);

    /**
     *卖家激活
     * @param SellerName
     * @return
     */
    boolean active(String SellerName);




}