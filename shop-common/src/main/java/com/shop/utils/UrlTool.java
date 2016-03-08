package com.shop.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求URL的相关工具类
 * Created by ldz on 17/12/14.
 */
public class UrlTool {
    private static Log log = LogFactory.getLog(UrlTool.class);
    /**
     * 格式化浏览器中中文字符，防止中文乱码
     * @param str
     * @return
     */

    public static String codeToString(String str){
        String result = null;
        try {
            //浏览器编码是"ISO-8859-1"
            result = new String(str.getBytes("ISO-8859-1"));
        }catch (Exception e){
            log.debug("浏览器编码转换错误");
            return  null;

        }
        return result;
    }

    /**
     * 获取本次请求的路径
     * @param request
     * @return
     */
    public static String getRequestURL(HttpServletRequest request){
        String url ="";
        try {
            url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+request.getServletPath();
            if(request.getQueryString()!=null)
                url = url+"?"+codeToString(request.getQueryString());
            url = java.net.URLEncoder.encode(url,"gbk");
        }catch (Exception e){
            log.error("浏览器编码转换错误");
            return null;
        }
        return url;
    }

}
