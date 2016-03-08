package com.shop.dao.test;

import com.shop.dao.SellerDao;
import com.shop.dao.impl.HibernateDao;
import com.shop.dao.impl.JdbcUserDaoImpl;
import com.shop.domain.Authority;
import com.shop.domain.Seller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by root on 14-12-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class SellerDaoTest {

    @Autowired
    JdbcUserDaoImpl dao;
    @Autowired
    HibernateDao hibernateDao;

    @Autowired
    SellerDao sellerDao;

    @Test
    public void createSellerTest(){
        Seller seller = new Seller();
        seller.setSellerName("MaiJia");
        seller.setId("3e17ef31b0c3466fa10a763caf8e4f11");
        this.sellerDao.createSeller(seller);
    }



}
