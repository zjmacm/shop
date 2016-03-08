package com.shop.service;

import com.shop.dao.Page;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by yj on 14-12-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-service.xml")
public class SearchServiceTest {

    @Autowired
    SearchService service;

    @Test
    public void simpleGoodsSearchTest() throws IOException, ParseException {
        Page page = service.simpleGoodsSearch("魅族高保温",1);
        System.out.println(page);
    }
}
