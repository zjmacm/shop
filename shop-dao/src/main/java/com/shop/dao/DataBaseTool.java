
package com.shop.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 导入Schema文件夹子下所有的sql文件
 * Created by ldz on 24/12/14.
 */
public class DataBaseTool {
    
    //数据库熟悉文件
    private  static String dataBasePro = "jdbc.properties";
    //驱动名
    private  static String driverClassName = " com.mysql.jdbc.Driver";
    private  static String url ;
    private  static String username;
    private  static String password;
    private  static String sqlDir ;
    private  static Log log = LogFactory.getLog(DataBaseTool.class);
    private  static Connection con ;
    public DataBaseTool() {

        Properties props = null ;
        try {
            props = Resources.getResourceAsProperties(dataBasePro);
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            driverClassName = props.getProperty("jdbc.driverClassName");

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            log.debug("dataBaseTool 初始化数据库异常");
        }

    }
    public void initConnection() {
        if (url != null) {


            try {
                Class.forName(driverClassName).newInstance();
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void runScript(){
        //Console console = System.console();
        //console.readLine("\n按control+c推出，按其它键继续安装。。。\n");
        if(con==null)
           initConnection();
        try {
                ScriptRunner runner = new ScriptRunner(con,false,true);
                runner.setErrorLogWriter(null);
                runner.setLogWriter(null);
                List<String> sqls = getScriptSql(sqlDir);
             for(String sql :sqls) {
                 System.err.println("loading..."+sql);

                 InputStreamReader reader =new InputStreamReader(new FileInputStream(
                            sql));
                    runner.runScript(reader);

                    reader.close();
                }

            } catch (Exception e){
                System.out.println("ERROR: " + e.getMessage());
                log.debug("数据库连接异常");

            }finally {
                if(con!=null)
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
            }


        }

    public List<String> getScriptSql(String dir){
        List<String> result = new ArrayList<>();
        File root = new File(dir);
        File[] files = root.listFiles();
        for(File file : files){
            if(file.isDirectory()){
                result.addAll(getScriptSql(file.getAbsolutePath()));
            }else {
                if(file.getName().endsWith("sql"))
                    result.add(file.getAbsolutePath());
            }
        }
        return  result;
    }

    public static String getDataBasePro() {
        return dataBasePro;
    }

    public static void setDataBasePro(String dataBasePro) {
        DataBaseTool.dataBasePro = dataBasePro;
    }

    public static String getDriverClassName() {
        return driverClassName;
    }

    public static void setDriverClassName(String driverClassName) {
        DataBaseTool.driverClassName = driverClassName;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DataBaseTool.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DataBaseTool.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataBaseTool.password = password;
    }

    public static String getSqlDir() {
        return sqlDir;
    }

    public static void setSqlDir(String sqlDir) {
        DataBaseTool.sqlDir = sqlDir;
    }
}
