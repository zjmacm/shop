package com.shop.quartz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by qf on 2014/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/quartz.xml"})
public class QuartzTest {

    @Autowired
    private QuartzJob quartzJob;
    @Test
    public void testExecute() throws Exception {
       quartzJob.execute();
    }
}
