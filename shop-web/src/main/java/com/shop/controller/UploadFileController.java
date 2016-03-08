package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by qf on 2014/12/10.
 */
@Controller
@RestController
@RequestMapping("/category")
public class UploadFileController {
    /**
     * 多图上传
     * @param appId
     * @param response
     * @param file
     * @throws IOException
     */
    @RequestMapping("/uploadFiles")
    public void uploadFiles(Integer appId, HttpServletResponse response,
                            @RequestParam("file") CommonsMultipartFile file) throws IOException {
        //applicationService.uploadFiles(appId, file);//此处问题待解决!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // EXTJS文件上传特殊返回
        Writer writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("{success: true}");
        writer.close();
    }
}
