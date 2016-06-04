package com.aaron.winkwebapp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aaron.winkwebapp.api.bean.User;
import com.aaron.winkwebapp.api.exception.UserException;
import com.aaron.winkwebapp.core.dao.IUserDao;
import com.aaron.winkwebapp.core.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Transactional(propagation=Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
			)
	@Override
	public int addUser(User user) throws UserException {
		
		return userDao.addUser(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
			)
	@Override
	public int removeUser(User user) throws UserException {
		return userDao.removeUser(user);
	}

	@Transactional(propagation=Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
			)
	@Override
	public int updateUser(User user) throws UserException {
		return userDao.updateUser(user);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
			isolation=Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public User getUserById(int id) throws UserException {
		return userDao.getUserById(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
			isolation=Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

}
