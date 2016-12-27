package com.aaron.shiro.showcase5.dao;

import com.aaron.shiro.showcase5.entity.User;

import java.util.Set;

/**
 * Created by Administrator on 2016/12/27.
 */
public interface UserDao {
    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
