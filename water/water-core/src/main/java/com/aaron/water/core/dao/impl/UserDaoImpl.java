package com.aaron.water.core.dao.impl;

import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;
import com.aaron.tools.template.MybatisTemplate;
import com.aaron.water.bean.User;
import com.aaron.water.core.dao.IUserDao;
import com.aaron.water.exception.WaterException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:用户信息相关操作Dao接口实现
 * @Author:Aaron.Qiu
 * @Since:2015-04-12
 * @Copyright:Copyright (c)  2014 ~ 2015 版权所有
 */
@Repository
public class UserDaoImpl extends MybatisTemplate implements IUserDao {

	@Override
	public int insertUser(User user) throws WaterException {
		return insert("UserXML.insertUser", user);
	}

	@Override
	public int updateUser(User user) throws WaterException {
		return this.update("UserXML.updateUser", user);
	}

	@Override
	public int deleteUserById(String userId) throws WaterException {
		return this.delete("UserXML.deleteUserById", userId);
	}

	@Override
	public User getUserById(String userId) throws WaterException {
		return this.selectOne("UserXML.getUserById", userId);
	}

	@Override
	public PagerModel<User> getPagerModelByQuery(Query query, User user)
			throws WaterException {
		return this.getPagerModelByQuery(user, query,"UserXML.getPagerModelByQuery");
	}

	@Override
	public List<User> getAll(User user) throws WaterException {
		return selectList("UserXML.getAll", user);
	}

}
