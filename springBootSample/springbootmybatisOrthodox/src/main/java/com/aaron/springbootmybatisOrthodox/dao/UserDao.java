package com.aaron.springbootmybatisOrthodox.dao;

import com.aaron.springbootmybatisOrthodox.bean.User;

import java.util.List;

/**
 * Created by Aaron.qiu on 2017/9/2.
 */
public interface UserDao {

    List<User> getAll();

    User getOne(String id);

    int insert(User user);

    int update(User user);

    int delete(String id);

}
