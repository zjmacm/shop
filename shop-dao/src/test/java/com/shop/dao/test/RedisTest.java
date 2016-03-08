package com.shop.dao.test;

import com.alibaba.fastjson.JSON;
import com.shop.dao.impl.RedisDao;
import com.shop.dao.lucene.SerialScoreDoc;
import com.shop.domain.User;
import com.shop.utils.Tool;
import org.apache.lucene.search.ScoreDoc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 11/21/14.
 * RedisTemplate 主要有两类操作
 * 键值绑定操作 和 非绑定操作
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class RedisTest {
    @Autowired
    RedisTemplate template;
    @Autowired
    RedisDao redisDao;

    @Test
    public void listTest(){

        template.boundListOps("yijun").leftPush("gaogao");
        template.opsForList().leftPush("yijun","apple");
        System.out.println(template.opsForList().index("yijun", 0));
    }

    @Test
    public void transactionTest(){
       // template.opsForValue().set("count1", 100);
        System.out.println(template.opsForValue().get("count1"));

        System.out.println(template.opsForValue().get("count1"));

    }

    @Test
    public void setTest(){

    }

    @Test
    public void piplingTest(){

        template.executePipelined(new RedisCallback<Object>() {
            Integer index = Integer.valueOf(1);
            @Override
            public Object doInRedis(RedisConnection redisConnection)
                    throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) redisConnection;
                for(int i = 0; i < 10; i++)
                {//increment by 10 each key
                    connection.incrBy(index.toString(),10);
                    index = Integer.valueOf(i);
                }
                return null;
            }
        });
    }

    @Test
    public void jsonTest(){
        List<Hi> list = new ArrayList<Hi>();
        list.add(new Hi("电子",null));
        list.add(new Hi("化妆",new ArrayList<String>()));
        String json = JSON.toJSONString(list,true);
        System.out.println(json);
    }

    @Test
    public void SerializeTest(){
        User user = new User();
        user.setId(Tool.getUUID32());
        user.setRealName("易君");
        user.setEmail("383548557@qq.com");
        System.out.println(user.getId());
        redisDao.saveObject(user.getId(),user);
        System.out.println(redisDao.getObject(user.getId()));
    }

    @Test
    public void toJsonTest(){
        User user = new User();
        user.setId(Tool.getUUID32());
        user.setRealName("易君");
        user.setEmail("383548557@qq.com");
        redisDao.saveObject(user.getId(), user);
        System.out.println(redisDao.getObjectAsJson(user.getId()));
    }



    @Test
    public void deSerializeTest(){
        User user = (User) redisDao.getObject("216eed6ac555419d84a533c1b1c61ec9");
        System.out.println(user);
    }



    @Test
    public void getJsonStringTest(){
        String json = redisDao.getObjectAsJson("4109aec45c984da68f986a403741b34d");
        System.out.println(json);
    }


    @Test
    public void ListPush(){
        User user = new User();
        String id = Tool.getUUID32();
        System.out.println(id);
        user.setId(id);
        user.setRealName("杨屈");
        user.setEmail("383548557@qq.com");
        redisDao.maintainListAdd("latest", user, 10);
        System.out.println(redisDao.listIndex("latest",0,100l, TimeUnit.MINUTES));
    }


    @Test
    public void ListGetAllTest(){
        List result = redisDao.listGetAll("latest");
        System.out.println(result);
    }

    @Test
    public void expireTest() throws InterruptedException {
        redisDao.saveObject("hehe","hello",1l,TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
        Object result = redisDao.getObject("hehe");
        System.out.println(result);
    }

    @Test
    public void listAddTest(){
        redisDao.listAdd("search.page.doc", new SerialScoreDoc().setScoreDoc(new ScoreDoc(1,2,3)));
        SerialScoreDoc result = (SerialScoreDoc) redisDao.listIndex("search.page.doc",0,null,null);
        System.out.println(result);
    }

    @Test
    public void listAddAllTset(){
        Object o = new SerialScoreDoc().setScoreDoc(new ScoreDoc(1,2,3));
        List list = new ArrayList();
        list.add(o);
        list.add(o);
        redisDao.listAddAll("score",list,10l,TimeUnit.MINUTES);
        System.out.println(redisDao.listGetAll("score"));
    }

    class Hi {
        String name;
        List<String> children;
        Hi(String name, List<String> children) {
            this.name = name;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getChildren() {
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Hi{" +
                    "name='" + name + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}
