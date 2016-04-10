package com.aaron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aaron.bean.User;
import com.aaron.dao.UserDao;
import com.aaron.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(propagation=Propagation.SUPPORTS,
			isolation=Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	@Transactional(propagation=Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
			)
	@Override
	public int updateUser(User u) {
		
		return userDao.updateUser(u);
	}

}
