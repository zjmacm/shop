package com.shop.image;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by yj on 14-11-22.
 *
 * 图片处理静态工具类,用到第三方图片处理类库Thumbnails
 */
public class ImageProcess {

    /**
     * 根据源图片路径生成缩略图,可以指定输出图片格式
     * @param src 源图片
     * @param height 缩略图片高度
     * @param width 缩略图片宽度
     * @param outputFormat 缩略图格式
     * @param des 缩略图输出文件
     * */
    public static void createThumbnail(File src, int height, int width,
                                File des, String outputFormat) throws IOException {
        Thumbnails.of(src)
                .size(width,height)
                .outputFormat(outputFormat)
                .toFile(des);
    }

    /**
     * 根据图片对象生成缩略图
     * 输出图片格式与源文件相同
     * */
    public static void createThumbnail(File src, int height, int width, File des)
            throws IOException {
        String format = getFormat(src);
        createThumbnail(src,height,width,des,format);
    }

    /**
     * 根据图片路径生成缩略图
     * 输出图片格式与源文件相同
     * */
    public static void createThumbnail(String srcPath, int height, int width, String desPath)
            throws IOException {
        File src = new File(srcPath);
        if(!src.exists())
            throw new FileNotFoundException();
        String format = getFormat(src);
        createThumbnail(src,height,width,new File(desPath),format);
    }


    private static String getFormat(File src){
        String format = src.getName();
        format = format.substring(format.lastIndexOf(".")+1);
        return format;
    }
}
