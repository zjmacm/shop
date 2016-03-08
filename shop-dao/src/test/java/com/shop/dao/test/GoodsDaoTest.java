package com.shop.dao.test;

import com.shop.dao.GoodsDao;
import com.shop.domain.Goods;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by yj on 14-11-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/shop-dao.xml")
public class GoodsDaoTest {

    @Autowired
    GoodsDao goodsDao;

    @Test
    public void createTest(){
        Goods goods = new Goods();
        goods.setCategoryId("ff80808149ebc2680149ebc269e10000");
        goods.setName("魅族");
        goods.setDescription("超值低价");
        goods.setPrice(988);
        goods.setAudit("0");
        String id = goodsDao.create(goods);
    }

    @Test
    public void getGoodsByIdTest(){
        Goods goods = goodsDao.getById("ff80808149fa6c070149fa6c094b0000");
        System.out.println(goods);
    }

    @Test
    public void getLatestTest(){
        List<Goods> latest = goodsDao.getLatest();
        System.out.println(latest);
    }

    @Test
    public void getByCategoryTest(){
        List<Goods> cat = goodsDao.getGoodsByCategory("ff80808149ebc2ac0149ebc2ae2c0000",1);
        System.out.println(cat);
    }

    @Test
    public void RecommendationTest(){

        for(int i = 0; i < 30 ; i++){
            Goods goods = new Goods();
            goods.setCategoryId("ff80808149ebc2ac0149ebc2ae2c0000");
            goods.setName("魅族");
            goods.setDescription("超值低价");
            goods.setPrice(988);
            goods.setAudit("0");
            goods.setScanTime(i);
            goodsDao.create(goods);
        }
        List<Goods> result = goodsDao.getRecommondation(2);
        System.out.println(result);

    }

    @Test
    public void regTest(){
        String ORIGIN_SQL = "select ${*} from shop_goods";
        String[] fields = {"id", "name"};
        StringBuilder builder = new StringBuilder();
        for(String str : fields){
            builder.append(str + ",");
        }
        builder.deleteCharAt(builder.length() - 1);//去掉最后一个逗号
        String result = ORIGIN_SQL.replaceFirst("\\$\\{\\*\\}",builder.toString());
        System.out.println(result);
        Assert.assertTrue(result.equals("select id,name from shop_goods"));
    }

    @Test
    public void getAllTest(){
        List list = goodsDao.getAllGoods(new String[]{"shop_goods.id", "name", "category"});
        System.out.println(list);
    }
}
