package com.shop.image;

import com.shop.utils.Tool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传对象封装
 * 多个地方可用
 * Created by ldz on 12/12/14.
 */
public class FileUpload {
    //暂时定在
    private final static String IMG_DESC_PATH =File.separator+"uploads";
    private final static int IMG_HEIGHT = 800;
    private final static int IMG_WIDTH = 600;
    //图片格式
    private final static List<String>FORMATE = Arrays.asList(new String []{"jpg","jpeg"});

    private  Log log = LogFactory.getLog(this.getClass());
    public static boolean checkFormate(MultipartFile file){

        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf(".")+1);
        return FORMATE.contains(extName)? true:false;

    }

    /**
     * 为图片产生uri 返回给前端
     * @param file
     * @return
     */
    public static String createUri(MultipartFile file,HttpServletRequest request){
        String imgPath = request.getSession().getServletContext().getRealPath(IMG_DESC_PATH)+File.separator;
        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));
        String newName = Tool.getUUID32()+extName;
        return imgPath+newName;
    }

    public  Boolean saveImg(File srcFile,File descFile){
        try{
            System.err.println("this is saveImag");
            ImageProcess.createThumbnail(srcFile,IMG_HEIGHT,IMG_WIDTH,descFile);
        }catch (IOException e){
           log.error("上传文件错误");
            System.err.println("缩放文件有错误");
            return false;
        }
        return true;
    }

    public static String getImgDescPath() {
        return IMG_DESC_PATH;
    }

    public static int getImgHeight() {
        return IMG_HEIGHT;
    }

    public static int getImgWidth() {
        return IMG_WIDTH;
    }

    public static List<String> getFormate() {
        return FORMATE;
    }
}
