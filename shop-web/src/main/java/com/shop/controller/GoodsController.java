package com.shop.controller;

import com.alibaba.fastjson.JSON;
import com.shop.dao.PageJdbc;
import com.shop.domain.Goods;
import com.shop.domain.Seller;
import com.shop.image.URIProcessor;
import com.shop.service.GoodsCommentService;
import com.shop.service.GoodsService;
import com.shop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by yj on 14-11-29.
 * 商品信息相关controller
 */
@Controller
@RequestMapping("/Goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private GoodsCommentService commentService;

    private final int LATEST_COUNT = 3;//首页最新商品推荐数量
    /**
     * 获取商品详情页面接口
     * @param sId 卖家id
     * @param gId 商品id
     * */

     @RequestMapping("/get")
    public ModelAndView getInfo(@RequestParam String sId,@RequestParam String gId){
        Seller seller = sellerService.getSeller(sId);
        Goods goods = goodsService.getGoodsById(gId);
        goodsService.updateScanTime(goods);//更新商品的浏览次数
        PageJdbc<Map<String, Object>> comments = commentService.getCommentsByGoodId(gId, 0 , 10);
        ModelAndView mav = new ModelAndView("goods");
        mav.addObject("seller", seller);
        mav.addObject("goods", goods);
        mav.addObject("comments", comments.getList());
        return mav;
    }

    //获取最新商品信息
    @RequestMapping(produces = {"application/json;charset=UTF-8"},value = "/latest")
    private String getLatest(){
        List<Map<String,String>> result = new ArrayList<>();
        List<Goods> goods = goodsService.getLatestGoods(LATEST_COUNT);
        for(Goods good : goods){
            Map goodMap = new HashMap<String, String>();
            String imageURI = good.getImages();
            URIProcessor processor = new URIProcessor(imageURI);
            goodMap.put("img",processor.getMainURI());
            goodMap.put("id", good.getId());
            goodMap.put("name", good.getName());
            goodMap.put("price", good.getPrice());
            goodMap.put("publishTime", good.getCreateTime());
            Map relatedInfo = goodsService.getRelatedInfo(good.getId());
            goodMap.putAll(relatedInfo);
            result.add(goodMap);
        }
        String Json = JSON.toJSONString(result);
        return Json;
    }
}
