package com.aaron.winkwebapp.core.dao;

import java.util.List;

import com.aaron.winkwebapp.api.bean.User;
import com.aaron.winkwebapp.api.exception.UserException;

public interface IUserDao {
	
	public int addUser(User user) throws UserException;
	
	public int removeUser(User user) throws UserException;
	
	public int updateUser(User user) throws UserException;
	
	public User getUserById(int id) throws UserException;
	
	public List<User> getUserList();
	
	
}
