package com.aaron.springbootDemo.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PingMapper {
    @Select("SELECT 'OK'")
    String ping();
}
