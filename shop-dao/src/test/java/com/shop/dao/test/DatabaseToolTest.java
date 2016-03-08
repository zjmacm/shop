package com.shop.dao.test;

import com.shop.dao.DataBaseTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ldz on 24/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class DatabaseToolTest {
    @Test
    public void runScript(){
        DataBaseTool  tool = new DataBaseTool();
        tool.setSqlDir("/home/ldz/Work/shop/shop-web/src/main/schema/");
        tool.runScript();
    }
}
