package com.shop.service.impl;

import com.shop.image.FileUpload;
import com.shop.service.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qf on 2014/12/10.
 */
@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService {
    //@Override
    //public void uploadFiles(int appId, CommonsMultipartFile logoFile) {
    //String filePath = savePicFile(logoFile);
    // Map<String,Object> mp = new HashMap<String,Object>();
    //mp.put("applicationId",appId);
    //mp.put("image",filePath);
    //applicationMapper.insertUploadImage(mp);
    //}

    @Override
    public void uploadFiles(int appId, CommonsMultipartFile logoFile) {

    }

    @Override
    public Map<String, Object> uploadGoodsIMG(List<MultipartFile>files, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (MultipartFile file : files) {
            if (!FileUpload.checkFormate(file)) {
                //有一个文件格式不对都不行
                String error = "formate is error,please check all files";
                result.put("error", error);
                return result;
            }
        }
        List<String> uris = new ArrayList<>();
        FileUpload fileUpload = new FileUpload();

        for (MultipartFile file : files) {
            String uri = FileUpload.createUri(file, request);
            try {
                File tmp = new File(uri);
                file.transferTo(tmp);
                uris.add(uri);
                if (!fileUpload.saveImg(tmp, new File(uri))) {
                    String error = "upload error,please try again";
                    result.put("error", error);
                    return result;
                }
            } catch (Exception e) {
                System.err.println("exception");
                String error = "upload error,please try again";
                result.put("error", error);
                return result;
            }
        }
        result.put("success","true");
        result.put("uris",uris);
        return result;
    }


}
