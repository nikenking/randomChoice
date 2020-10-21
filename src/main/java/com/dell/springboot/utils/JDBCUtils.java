package com.dell.springboot.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
* @autor 李媛媛
* @date 2020/10/14 10:08
* @desc 操作数据库的工具类
*/
public class JDBCUtils {

    //获取连接的静态方法
    public static Connection getConnection() throws Exception {
        //1.读取配置文件中的四个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        String driver = pros.getProperty("driver");
        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        //2.加载驱动
        Class.forName(driver);
        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    //关闭资源1
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭资源2
    public static void closeResource(Connection conn, Statement ps,ResultSet rst) {
        try {
            if (ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rst!=null){
                rst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
