package com.shop.utils;

import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * Created by ldz on 21/11/14.
 * @author ldz
 */
public class Tool {

    /**
     * 线程安全变量，同一个线程在不同文件之间传递数据
     * 使用：
     * Tool.threadLocal.set(Map<String,Object>)
     * Tool.threadLocal.get.get(String key)
     */
    public static ThreadLocal<Map<String,Object>> threadLocal =
            new ThreadLocal<Map<String,Object>>();

    /**
     * 返回标准的UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 返回UUID字符串，长度为32个字符
      */
    public static String getUUID32() {
        String uuid = getUUID();
        return uuid.replace("-", "");
    }

    /**
     * 两个时间差，单位是秒
     * @param date1
     * @param date2
     * @return
     */
    public  static long differ(Date date1, Date date2)
    {
        return date2.getTime()-date1.getTime();

    }

    /**
     * 字节数组转换为BASE64编码字符串，没有回车换行
     * @param data 字节数组
     * @return BASE64编码字符串
     */
    public static String toBase64(byte[] data) {
        if (data == null || data.length == 0) {
            return new String();
        }
        try {
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(data, false), "ISO-8859-1");
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 字节数组转换为BASE64编码字符串，没有回车换行
     * @param data 字节数组
     * @return BASE64编码字符串
     */
    public static String toBase64Chunked(byte[] data) {
        if (data == null || data.length == 0) {
            return new String();
        }
        try {
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(data, true), "ISO-8859-1");
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 将BASE64字符串转换为字节数组
     * @param data BASE64编码的字符串
     * @return 字节数组
     */
    public static byte[] convertBase64ToByteArray(String data) {
        if (data == null || data.length() == 0) {
            return new byte[0];
        }
        return org.apache.commons.codec.binary.Base64.decodeBase64(data);
    }



    //---将各种类型的数据转换为字节数组，big-endian---//

    /**
     *将一个短整数数据转换为字节数组
     * @param s 短整数数据
     * @return 字节数组
     */
    public static byte[] toByteArray(short s) {
        byte[] buffer = new byte[2];
        buffer[0] = (byte) (s >> 8 & 0xff);
        buffer[1] = (byte) (s & 0xff);
        return buffer;

    }

    /**
     *将一个整数数据转换为字节数组
     * @param i 短整数数据
     * @return 字节数组
     */
    public static byte[] toByteArray(int i) {
        byte[] buffer = new byte[4];
        buffer[0] = (byte) (i >> 24 & 0xff);
        buffer[1] = (byte) (i >> 16 & 0xff);
        buffer[2] = (byte) (i >> 8 & 0xff);
        buffer[3] = (byte) (i & 0xff);
        return buffer;

    }

    /**
     *将一个长整数数据转换为字节数组
     * @param l 短整数数据
     * @return 字节数组
     */

    public static byte[] toByteArray(long l) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (l >> ((8 - i - 1) * 8) & 0xff);
        }
        return buffer;
    }

    /**
     *将一个单精度浮点数据转换为字节数组
     * @param f 短整数数据
     * @return 字节数组
     */
    public static byte[] toByteArray(float f) {
        return toByteArray(Float.floatToIntBits(f));
    }

    /**
     * 将一个双精度浮点数据转换为字节数组
     * @param d 短整数数据
     * @return 字节数组
     */
    public static byte[] toByteArray(double d) {
        return toByteArray(Double.doubleToLongBits(d));
    }

    /**
     *将2个字节组装成一个短整数类型的数据
     * @param data 2个字节
     * @return 短整数
     */
    public static short getShort(byte[] data) {
        short s = 0;
        s = (short) (s | (data[0] << 8 & 0xff00));
        s = (short) (s | (data[1] & 0xff));
        return s;

    }

    /**
     *将4个字节组装成一个整数类型的数据
     * @param data 4个字节
     * @return 整数
     */
    public static int getInt(byte[] data) {
        int i = 0;
        i = i | (data[0] << 24 & 0xff000000);
        i = i | (data[1] << 16 & 0xff0000);
        i = i | (data[2] << 8 & 0xff00);
        i = i | (data[3] & 0xff);
        return i;
    }

    /**
     *将8个字节组装成一个长整数类型的数据
     * @param data 8个字节
     * @return 长整数
     */
    public static long getLong(byte[] data) {
        long l = 0;
        for (int i = 0; i < 8; i++) {
            l = l | ((long) data[i] << ((8 - i - 1) * 8) & (0xffL << (8 - i - 1) * 8));
        }
        return l;
    }

    /**
     *将4个字节组装成一个单精度浮点数类型的数据
     * @param data 4个字节
     * @return 浮点数数
     */
    public static float getFloat(byte[] data) {
        return Float.intBitsToFloat(getInt(data));
    }

    /**
     *将8个字节组装成一个双精度浮点数类型的数据
     * @param data 8个字节
     * @return 双精度数
     */
    public static double getDouble(byte[] data) {
        return Double.longBitsToDouble(getLong(data));
    }

    /**
     *返回GBK编码格式的字符串对应的字节数组
     * @param s GBK编码字符串
     * @return GBK编码字节数组
     */
    public static byte[] getGbkBytes(String s) {
        try {
            return s.getBytes("GBK");
        } catch (Exception e) {
            return new byte[0];
        }
    }

    /**
     *将GBK编码格式的字节数组转换为UNICODE字符串，即JAVA字符串
     * @param buffer GBK编码格式的字节数组
     * @return UNICODE字符串
     */
    public static String toStringFromGbk(byte[] buffer) {
        try {
            return new String(buffer, "GBK");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *返回UTF8编码格式的字符串对应的字节数组
     * @param s  UTF8编码格式的字符
     * @return UTF8字节数组
     */
    public static byte[] getUtf8Bytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (Exception e) {
            return new byte[0];
        }
    }

    /**
     *将UTF-8编码格式的字节数组转换为UNICODE字符串，即JAVA字符串
     * @param buffer UTF8编码格式的字节数组
     * @return UNICODE字符串
     */
    public static String toStringFromUtf8(byte[] buffer) {
        try {
            return new String(buffer, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *返回UNICODE编码格式的字符串对应的字节数组，高位字节数据在前
     * @param s  UNICODE编码格式的字符
     * @return 字节数组
     */
    public static byte[] getUnicodeBytes(String s) {
        try {
            return s.getBytes("UnicodeBigUnmarked");
            // return s.getBytes("UnicodeLittleUnmarked");
            // return s.getBytes("Unicode"); 0xFEFF prefix
        } catch (Exception e) {
            return new byte[0];
        }
    }

    /**
     *将UNICODE编码格式的字节数组转换为UNICODE字符串，即JAVA字符串
     * @param buffer  UNICODE编码格式的字节数组
     * @return UNICODE字符串
     */
    public static String toStringFromUnicode(byte[] buffer) {
        try {
            return new String(buffer, "UnicodeBigUnmarked");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *将可序列化的对象转换为字节数组
     * @param object  可序列化的对象
     * @return 字节数组
     */
    public static byte[] serialize(java.io.Serializable object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            baos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    /**
     *将可序列化的对象序列化到字节输出流中
     * @param object 可序列化对象
     * @param os 输出流
     */
    public static void serialize(java.io.Serializable object, OutputStream os) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
        }
    }

    /**
     *将字节数据反序列化为对象
     * @param data 字节数据
     * @return 对象
     */
    public static Object deserialize(byte[] data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *从流中读取一个对象数据，反序列化
     * @param is 流
     * @return 对象
     */
    public static Object deserialize(InputStream is) {
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *将对象数据转换为JSON格式的字符串
     * @param o 对象
     * @return json串
     */
    public static String toJSONString(Object o) {
        return com.alibaba.fastjson.JSON.toJSONString(o);
    }

    /**
     *将JSON串转换为指定类型的对象
     * @param jsonString json串
     * @param t 指定类型
     * @return 指定类型对象
     */
    public static <T> T parseJSONString(String jsonString, Class<T> t) {
        return (T) com.alibaba.fastjson.JSON.parseObject(jsonString, t);
    }

}
