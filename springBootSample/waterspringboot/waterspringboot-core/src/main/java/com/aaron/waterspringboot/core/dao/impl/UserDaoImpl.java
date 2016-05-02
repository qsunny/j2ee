package com.aaron.waterspringboot.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaron.waterspringboot.bean.User;
import com.aaron.waterspringboot.core.dao.IUserDao;
import com.aaron.waterspringboot.exception.WaterSpringbootException;
import com.cecport.tools.pager.PagerModel;
import com.cecport.tools.pager.Query;
import com.cecport.tools.template.MybatisTemplate;

/**
 * @Description:用户信息相关操作Dao接口实现
 * @Author:Aaron.Qiu
 * @Since:2015-04-12
 * @Copyright:Copyright (c)  2014 ~ 2015 版权所有
 */
@Repository
public class UserDaoImpl extends MybatisTemplate implements IUserDao {

	@Override
	public int insertUser(User user) throws WaterSpringbootException {
		return insert("UserXML.insertUser", user);
	}

	@Override
	public int updateUser(User user) throws WaterSpringbootException {
		return this.update("UserXML.updateUser", user);
	}

	@Override
	public int deleteUserById(String userId) throws WaterSpringbootException {
		return this.delete("UserXML.deleteUserById", userId);
	}

	@Override
	public User getUserById(String userId) throws WaterSpringbootException {
		return this.selectOne("UserXML.getUserById", userId);
	}

	@Override
	public PagerModel<User> getPagerModelByQuery(Query query, User user)
			throws WaterSpringbootException {
		return this.getPagerModelByQuery(user, query,"UserXML.getPagerModelByQuery");
	}

	@Override
	public List<User> getAll(User user) throws WaterSpringbootException {
		return selectList("UserXML.getAll", user);
	}

}
