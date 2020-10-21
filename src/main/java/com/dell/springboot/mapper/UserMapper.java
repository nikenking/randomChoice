package com.dell.springboot.mapper;

import com.dell.springboot.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface UserMapper {
//    @Select("select upass from managers where uname=#uname")
//    public String getUpass(String uname);
    @Select("select * from student")
    public List<User> getAll();
    @Update("update student set weight= 0 where name=#{t.name}")
    public void changeTeacher(@Param("t") User user);
}
