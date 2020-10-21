package com.dell.springboot.utils;
import com.dell.springboot.entities.User;
import org.testng.annotations.AfterTest;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    //测试
    @AfterTest
    public void test() throws Exception {
        String sql = "select name,weight from student";
        List<Student> list = getForList(Student.class, sql);
        list.forEach(System.out::println);
    }

    public <T> List<T> getForList(Class<T> clazz, String sql) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        //执行。返回结果集
        ResultSet rst = ps.executeQuery();
        //获取结果集的元数据
        ResultSetMetaData rsmd = rst.getMetaData();
        //通过元数据获取结果集中的列数
        int columnCount = rsmd.getColumnCount();
        //创建集合对象
        ArrayList<T> list = new ArrayList<T>();
        while (rst.next()) {
            T t = clazz.newInstance();

            //处理结果集的一行数据中的每一个列，给t对象指定的属性赋值
            for (int i = 0; i < columnCount; i++) {
                //获取列值
                Object columnValue = rst.getObject(i + 1);
                //获取每个列的列名
                //获取列的别名：rsmd.getColumnLabel();
                String columnName = rsmd.getColumnName(i + 1);
                //给cust对象指定的columnName属性，赋值为columnValue，通过反射
                Field field = User.class.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t, columnValue);
            }
            list.add(t);
        }
        JDBCUtils.closeResource(conn, ps, rst);
        return list;
    }
}
