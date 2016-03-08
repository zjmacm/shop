package com.shop.service.impl;

import com.shop.dao.SellerDao;
import com.shop.dao.UserDao;
import com.shop.domain.Seller;
import com.shop.domain.User;
import com.shop.service.Constants;
import com.shop.service.SellerService;
import com.shop.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 14-11-30.
 */
@Service("sellerService")
public class SellerServiceImpl implements com.shop.service.SellerService {

    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private UserContext userContext;

    @Override
    public Seller getSeller(String id) {
        return sellerDao.getSeller(id);
    }

    @Override
    public Seller findSellerByEmail(String email) {
        return sellerDao.findSellerByEmail(email);
    }

    @Override
    public Seller findSellerBySellerName(String sellerName) {
        return sellerDao.findSellerBySellerName(sellerName);
    }

    @Override
    public String createSeller(Seller seller) {
        return sellerDao.createSeller(seller);
    }

    @Override
    public boolean register(Seller seller, boolean flag) {
        //find whether the seller's name has existed
        if(seller == null){return false;}
        Seller _seller = this.sellerDao.findSellerBySellerName(seller.getSellerName());
        //if has existed,return false to notify the name has existed
        if(_seller!=null){return false;}
        //not existed,insert it
        seller.setSellerStatus(String.valueOf(Constants.DISABLED));  // "0"表示现在还没有激活
        final String uuid = this.sellerDao.createSeller(seller);
        if(uuid==null){return false;}
        return true;
    }

    @Override
    public boolean active(String sellerName) {
        Seller seller = this.sellerDao.findSellerBySellerName(sellerName);
        if(seller.getSellerStatus() == String.valueOf(Constants.WAIT_ENABLE)){
            seller.setSellerStatus(String.valueOf(Constants.ENABLE));
            return this.sellerDao.save(seller)!=null;
        }
        return false;
    }
}
