package com.aaron.dao;

import java.util.List;

import com.aaron.bean.User;

public interface UserDao {
	
	public List<User> getUserList();
	
	public int updateUser(User u);
	
}
