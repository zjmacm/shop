package com.shop.file;

import org.springframework.util.Assert;

import java.io.*;

/**
 * Created by yj on 14-11-22.
 * 文件持久化工具类
 */
public class PersistFile {

    private static final int BUFFER_SIZE = 16 * 1024;

    /**
     * 文件保存静态方法
     * @param src 要保存的源文件
     * @param des 要保存的目的路径文件
     *
     * */
    public static void save(File src, File des){
        InputStream in = null;
        OutputStream out = null;
        //外层保证源文件存在
        Assert.isTrue(src.exists());

        try {
            if(!des.exists()){
                des.createNewFile();
            }
            in = new BufferedInputStream(
                    new FileInputStream(src),BUFFER_SIZE);
            out = new BufferedOutputStream(
                    new FileOutputStream(des),BUFFER_SIZE);

            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while((len = in.read(buffer)) > 0){
                out.write(buffer,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
