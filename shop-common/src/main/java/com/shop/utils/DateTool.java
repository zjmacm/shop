package com.shop.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yj on 14-11-26.
 * 日期操作工具类
 */
public class DateTool {

    public static final String format = "yyyy-MM-dd HH:mm";

    private static DateFormat df = new SimpleDateFormat(format);

    public static String getCurrentTime(){
        return df.format(new Date());
    }

    public static long getCurrentDate(){
        return new Date(){}.getTime();
    }
    public static Date toDateFromString(String date) throws ParseException {
        return df.parse(date);
    }


}
