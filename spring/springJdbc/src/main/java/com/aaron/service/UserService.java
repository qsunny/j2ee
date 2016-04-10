package com.aaron.service;

import java.util.List;

import com.aaron.bean.User;

public interface UserService {
	
	public List<User> getUserList();
	
	public int updateUser(User u);
}
