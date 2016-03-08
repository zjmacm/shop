package com.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.domain.Category;
import com.shop.domain.Goods;
import com.shop.domain.Seller;
import com.shop.domain.Seller;
import com.shop.email.EmailSender;
import com.shop.image.FileUpload;
import com.shop.service.CategoryService;
import com.shop.service.GoodsService;
import com.shop.service.SellerService;
import com.shop.service.UploadFileService;
import com.shop.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by root on 14-12-3.
 */

@RestController
@Controller("sellerController")
@RequestMapping("/seller")
public class SellerController {

    public static final String HOST_NAME = "http://localhost:8088";

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
    @Autowired
    private SellerService sellerService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
//        if(Seller == null){
//            return modelAndView;
//        }
//
//        SellerService.register(Seller);
        modelAndView.setViewName("register2");
        return modelAndView;

    }
    @RequestMapping("/index")
    public ModelAndView sellerIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller");
        return modelAndView;

    }

    //注册全部信息验证
    @RequestMapping(value = "/register/register",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> doRegister(@Valid Seller seller,BindingResult result,boolean flag){
        Map<String ,Object> map = getResponseMap();

        if(result!=null && result.hasErrors()) {
            map.put("success",false);
            map.put("error", toJSON(result.getFieldErrors()));
            return map;
        }
        boolean insertSuccess = sellerService.register(seller,flag);
        if(!insertSuccess){
            map.put("success",false);
            map.put("error","用户名已存在！");
        }
        //注册成功，发送邮箱
        sendActiveEmail(seller);
        return map;
    }

    //注册激活active
    @RequestMapping(value = "/register/active",method = RequestMethod.GET)
    public  ModelAndView doActive(@RequestParam Map<String,Object> params){
        ModelAndView modelAndView = new ModelAndView();
        final String sellerName = (String) params.get("sellerName");
        if(sellerName == null){
            modelAndView.setViewName("error");
            return modelAndView;
        }
        boolean activeSuccess = sellerService.active(sellerName);
        if(activeSuccess){
            modelAndView.setViewName("active");
        }else{
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    //验证用户名是否合法
    @RequestMapping(value="/validate/sellername",method =  RequestMethod.POST)
    public @ResponseBody Map<String,Object> doValidateSellerName(String sellerName){
        Map<String,Object> map = getResponseMap();
        final String _name = sellerName==null?"":sellerName.trim();
        int length = _name.length();
        if(length<2 || length>14){
            map.put("success", false);
            map.put("error", "用户名长度只能在2-14之间");
            return map;
        }
        if(!Pattern.matches("[A-Z a-z][A-Z a-z 0-9 _]*", _name)){
            map.put("success",false);
            map.put("error","用户名格式错误");
        }
        return map;
    }



    @RequestMapping(value="/validate/email",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> doValidateEmail(String email){
        Map<String,Object> map = getResponseMap();
        final String _email = email==null?"":email.trim();
        boolean p = Pattern.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", _email);
        if(!p){
            map.put("success",false);
            map.put("error","邮箱格式错误");
        }
        return map;
    }

    @RequestMapping(value="/validate/password",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> doValidatePassword(String password){
        Map<String,Object> map = getResponseMap();
        System.out.println("password:"+password);
        final String _password = password==null?"":password.trim();
        int length = _password.length();
        if(length<6 || length>20){
            map.put("success",false);
            map.put("error", "密码长度只能在6-20之间");
        }
        return map;
    }

    //生成一个格式化的map
    private Map<String,Object> getResponseMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        map.put("error","");
        return map;
    }


    /**
     * 将错误信息以json格式返回
     * @param fieldErrors 错误的FieldError列表
     * @return json形式的错误信息，键值同表单中的名字
     */

    private String toJSON(List<FieldError> fieldErrors){
        JSONObject jsonObject = new JSONObject();
        for(FieldError error:fieldErrors){
            jsonObject.put(error.getField(),error.getDefaultMessage());
        }
        return jsonObject.toString();
    }

    /**
     *发送激活邮件
     * @param Seller 接收邮件的用户
     */
    private void sendActiveEmail(Seller Seller){
        Map<String,Object> context= new HashMap<String,Object>();
        //final String activeCode = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        final String activeCode = UUID.randomUUID().toString(); //the random active code
        final String SellerName = Seller.getSellerName();
        final String activeUrl = genereateUrlByGet(SellerName,activeCode);
        context.put("message","donahue");
        context.put("SellerName",Seller.getSellerName());
        context.put("activeUrl",activeUrl);
        final String receivers ="shang_ming_yang@163.com";
        emailSender.sendTemplateMail(receivers, "激活账户", "emailtemplates/activeEmail.vm", context, true);
    }

    /**
     * generate the activeUrl
     * @param SellerName
     * @param activeCode random active code
     * @return the activeUrl
     */
    private String genereateUrlByGet(String SellerName, String activeCode) {
        return new StringBuffer(HOST_NAME)
                .append("?SellerName=")
                .append(SellerName)
                .append("&activeCode=")
                .append(activeCode)
                .toString();
    }


    /**
     * 商品管理---增加商品
     * 返回一级分类
     * @return
     */
    @RequestMapping(value = "/goodsManager/addGoods",method = RequestMethod.GET)
    public @ResponseBody ModelAndView addGoods(){
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categorys = categoryService.getAllParentCategory();
        modelAndView.addObject("categorys",categorys);

        return modelAndView;
    }

    /**
     * restful根据parentId获取子分类
     * @param parentId
     * @return
     */

    @RequestMapping(value = "/goodsManager/addGoods/category/{parentId}",method = RequestMethod.GET)
    public @ResponseBody ModelAndView getCategoryByParentId(@PathVariable(value="parentId")String parentId){
        ModelAndView modelAndView = new ModelAndView();
        Category category = new Category();
        category.setId(parentId);
        List<Category> categorys = categoryService.getChildByCategory(category);
        modelAndView.addObject("categorys",categorys);
        return modelAndView;
    }



    /**
     * 处理商品详细信息表单的的提交
     */
    @RequestMapping(value = "/goodsManager/addGoods",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> addGoods(@Valid Goods goods,@RequestParam(value = "files",required = true)MultipartFile[] files,

                                                     HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        List<MultipartFile>data = new ArrayList<>();
        for(MultipartFile file : files){
            if(!file.isEmpty())
                data.add(file);
        }
        Map<String,Object>resultUpload = uploadFileService.uploadGoodsIMG(data,request);
        if(resultUpload.get("success")!="true")
            return resultUpload;
        //设置审核未通过
        goods.setAudit("0");
        String id = goodsService.create(goods);
        result.putAll(resultUpload);
        result.put("id",id);
        return result;
    }
    @RequestMapping(value = "/goodsManager/goodsFileUpload",method =RequestMethod.GET)
    public ModelAndView goodsFileUpload(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload_file");
        return modelAndView;
    }

    /**
     * 商品文件上传
     * 产生相关的uri返回给卖家管理界面作为表单的部分内容提交
     * @return
     */

    @RequestMapping(value = "/goodsManager/goodsFileUpload",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> goodsFileUpload(@RequestParam(value = "files",required = true)MultipartFile[] files,

                                                            HttpServletRequest request){

        List<MultipartFile>data = new ArrayList<>();
        for(MultipartFile file : files){
            if(!file.isEmpty())
                data.add(file);
        }
        return uploadFileService.uploadGoodsIMG(data,request);

    }

}
