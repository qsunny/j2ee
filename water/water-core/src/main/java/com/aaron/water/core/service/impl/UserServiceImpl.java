package com.aaron.water.core.service.impl;

import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;
import com.aaron.water.bean.User;
import com.aaron.water.core.dao.IUserDao;
import com.aaron.water.core.service.IUserService;
import com.aaron.water.exception.WaterException;
import org.springframework.stereotype.Service;

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
	
	@Override
	public int insertUser(User user) throws WaterException {
		int iFlag =userDao.insertUser(user);
		
		return iFlag;
	}

	@Override
	public int updateUser(User user) throws WaterException {
		int uFlag = userDao.updateUser(user);
		int i = 1/0;
		return uFlag;
	}

	@Override
	public int deleteUserById(String userId) throws WaterException {
		int uFlag = userDao.deleteUserById(userId);
		int i = 1/0;
		return uFlag;
	}

	@Override
	public User getUserById(String userId) throws WaterException {
		return userDao.getUserById(userId);
	}

	@Override
	public PagerModel<User> getPagerModelByQuery(Query query, User user)
			throws WaterException {
		return userDao.getPagerModelByQuery(query, user);
	}

	@Override
	public List<User> getAll(User user) throws WaterException {
		return userDao.getAll(user);
	}

}
