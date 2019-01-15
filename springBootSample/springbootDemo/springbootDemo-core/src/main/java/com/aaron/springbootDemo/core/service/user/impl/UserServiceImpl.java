package com.aaron.springbootDemo.core.service.user.impl;


import javax.annotation.Resource;

import com.aaron.springbootDemo.bean.User;
import com.aaron.springbootDemo.core.dao.user.IUserDao;
import com.aaron.springbootDemo.core.service.user.IUserService;
import com.allchips.common.base.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* <p>IUserService接口实现类</p>
* <p> aaron.qiu </p>
* <p>Version:1.0.0</p>
* <p>Create Date:2019-01-14 14:47:50 </p>
* <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
*/
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Transactional(propagation=Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public User getUserById(String id) throws Exception {
		return this.userDao.getUserById(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public List<User> getAll(User user) throws Exception {
		return this.userDao.getAll(user);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public Page<User> getPagerModelByQuery(User user, Page<User> page)
			throws Exception {
		List<User> result = this.userDao.getPagerModelByQuery(user, page);
		page.setResult(result);
		return page;
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int insertUser(User user) throws Exception {
		return this.userDao.insertUser(user);
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int delUserById(String id) throws Exception {
		return this.userDao.delUserById(id);
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int updateUser(User user) throws Exception {
		return this.userDao.updateUser(user);
	}
}

