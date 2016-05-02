package com.aaron.waterspringboot.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aaron.waterspringboot.bean.User;
import com.aaron.waterspringboot.core.dao.IUserDao;
import com.aaron.waterspringboot.core.service.IUserService;
import com.aaron.waterspringboot.exception.WaterSpringbootException;
import com.cecport.tools.pager.PagerModel;
import com.cecport.tools.pager.Query;

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
	
	@Override
	public int insertUser(User user) throws WaterSpringbootException {
		int iFlag =userDao.insertUser(user);
		
		return iFlag;
	}

	@Override
	public int updateUser(User user) throws WaterSpringbootException {
		int uFlag = userDao.updateUser(user);
		int i = 1/0;
		return uFlag;
	}

	@Override
	public int deleteUserById(String userId) throws WaterSpringbootException {
		int uFlag = userDao.deleteUserById(userId);
		int i = 1/0;
		return uFlag;
	}

	@Override
	public User getUserById(String userId) throws WaterSpringbootException {
		return userDao.getUserById(userId);
	}

	@Override
	public PagerModel<User> getPagerModelByQuery(Query query, User user)
			throws WaterSpringbootException {
		return userDao.getPagerModelByQuery(query, user);
	}

	@Override
	public List<User> getAll(User user) throws WaterSpringbootException {
		return userDao.getAll(user);
	}

}
