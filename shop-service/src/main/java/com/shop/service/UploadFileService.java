package com.shop.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by qf on 2014/12/10.
 */
public interface UploadFileService {
    public void uploadFiles(int appId, CommonsMultipartFile logoFile);

    /**
     * 多商品文件图片上传
     * @param files
     * @return 返回结果
     */
    public Map<String,Object> uploadGoodsIMG(List<MultipartFile>files,HttpServletRequest request);
}
