package com.dell.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;


@Mapper
public interface LoginMapper {
    @Select("select upass from managers where uname=#uname")
    public String getUpass(String uname);
}
