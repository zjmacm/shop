package com.shop.image;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yj on 15-2-5.
 * 用于处理数据库保存的图片路径
 * 保存格式是:uri|uri|uri,用|分割
 */
public class URIProcessor {

    String URIS;//对应数据库保存结果

    final int MAX_COUNT = 4;//可保存的最大URI数

    int cur_count;//现存的URI数量

    List<String> uris;

    public URIProcessor(String URIS){
        Assert.isTrue(URIS != null);
        this.URIS = URIS;
        String[] uri = URIS.split("\\|");
        this.uris = new ArrayList<String>(Arrays.asList(uri));
        this.cur_count = uri.length;
    }

    public boolean addURI(String uri){
        if(cur_count < MAX_COUNT){
            uris.add(uri);
            return true;
        }
        return false;
    }

    public List<String> getURISArray(){
        return this.uris;
    }

    public String getResult(){
        StringBuilder sb = new StringBuilder();
        for (String s : uris){
            sb.append(s).append("|");
        }
        sb.deleteCharAt(sb.lastIndexOf("|"));
        return sb.toString();
    }

    public String getMainURI(){
        return this.uris.get(0);
    }

    public boolean update(int index, String value){
        if(index < this.uris.size()){
            uris.set(index, value);
            return true;
        }
        return false;
    }

}
