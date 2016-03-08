package com.shop.image;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by yj on 15-2-5.
 */
public class URIProcessorTest {

    @Test
    public void test(){
        String from = "http://112.321.12.1:8080/fd/fdas.jpg|" +
                "http://112.321.12.1:8080/fd/fda.jpg|" +
                "http://112.321.12.1:8080/fd/fds.jpg";
        String[] re = {"http://112.321.12.1:8080/fd/fdas.jpg|",
                "http://112.321.12.1:8080/fd/fda.jpg|",
                "http://112.321.12.1:8080/fd/fds.jpg"
        };
        URIProcessor processor = new URIProcessor(from);
        List<String> result = processor.getURISArray();
        Assert.assertTrue(result.size() == 3);
        int i = 0;
        for(String s : result) {
            //Assert.assertTrue(s.equals(re[i++]));
            System.out.println(s);
        }
        processor.addURI("http://112.321.12.1:8080/fds.jpg");
        processor.addURI("http://112.321.12.1:8080/fds.png");
        String toStore = processor.getResult();
        Assert.assertTrue(toStore.equals(from + "|http://112.321.12.1:8080/fds.jpg" +
                "|http://112.321.12.1:8080/fds.png"));
    }
}
