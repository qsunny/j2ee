package com.aaron.springbootApp.core.service.impl;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.dao.IUserDao;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.exception.SpringbootAppException;
import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



/**
 * @Description:用户信息相关操作service接口实现
 * @Author:Aaron.Qiu
 * @Since:2015-04-12
 * @Copyright:Copyright (c)  2014 ~ 2015 版权所有
 */
@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Transactional(propagation= Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
	)
	@Override
	public int insertUser(User user) throws SpringbootAppException {
		int iFlag =userDao.insertUser(user);
		
		return iFlag;
	}

	@Transactional(propagation= Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
	)
	@Override
	public int updateUser(User user) throws SpringbootAppException {
		int uFlag = userDao.updateUser(user);
		int i = 1/0;
		return uFlag;
	}

	@Transactional(propagation= Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
	)
	@Override
	public int deleteUserById(String userId) throws SpringbootAppException {
		int uFlag = userDao.deleteUserById(userId);
		int i = 1/0;
		return uFlag;
	}

	@Transactional(propagation=Propagation.SUPPORTS,
			isolation= Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public User getUserById(String userId) throws SpringbootAppException {
		return userDao.getUserById(userId);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
			isolation=Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public PagerModel<User> getPagerModelByQuery(Query query, User user)
			throws SpringbootAppException {
		return userDao.getPagerModelByQuery(query, user);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
			isolation=Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public List<User> getAll(User user) throws SpringbootAppException {
		return userDao.getAll(user);
	}

}
