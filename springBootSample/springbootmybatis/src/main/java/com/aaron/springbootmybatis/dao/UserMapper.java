package com.aaron.springbootmybatis.dao;

import com.aaron.springbootmybatis.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Aaron.qiu on 2017/9/2.
 */
public interface UserMapper {

    @Select("SELECT * FROM tbl_user")
    @Results({
            @Result(property = "id",  column = "id", javaType = String.class),
            @Result(property = "username",  column = "username", javaType = String.class),
            @Result(property = "password",  column = "password", javaType = String.class),
            @Result(property = "age", column = "age",javaType = Integer.class)
    })
    List<User> getAll();

    @Select("SELECT * FROM tbl_user WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id", javaType = String.class),
            @Result(property = "username",  column = "username", javaType = String.class),
            @Result(property = "password",  column = "password", javaType = String.class),
            @Result(property = "age", column = "age",javaType = Integer.class)
    })
    User getOne(String id);

    @Insert("INSERT INTO tbl_user(id,username,password,age) VALUES(#{id},#{username},#{password}, #{age})")
    int insert(User user);

    @Update("UPDATE tbl_user SET username=#{username},password=#{password},age=#{age} WHERE id =#{id}")
    int update(User user);

    @Delete("DELETE FROM tbl_user WHERE id =#{id}")
    int delete(String id);

}
