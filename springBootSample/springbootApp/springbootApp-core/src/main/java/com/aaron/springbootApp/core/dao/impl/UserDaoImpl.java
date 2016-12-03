package com.aaron.springbootApp.core.dao.impl;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.dao.IUserDao;
import com.aaron.springbootApp.exception.SpringbootAppException;
import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;
import com.aaron.tools.template.MybatisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Description:用户信息相关操作Dao接口实现
 * @Author:Aaron.Qiu
 * @Since:2016-12-03
 * @Copyright:Copyright (c)  2016 ~ 2017 版权所有
 */
@Repository
public class UserDaoImpl extends MybatisTemplate implements IUserDao {

	@Override
	public int insertUser(User user) throws SpringbootAppException {
		return insert("UserXML.insertUser", user);
	}

	@Override
	public int updateUser(User user) throws SpringbootAppException {
		return this.update("UserXML.updateUser", user);
	}

	@Override
	public int deleteUserById(String userId) throws SpringbootAppException {
		return this.delete("UserXML.deleteUserById", userId);
	}

	@Override
	public User getUserById(String userId) throws SpringbootAppException {
		return this.selectOne("UserXML.getUserById", userId);
	}

	@Override
	public PagerModel<User> getPagerModelByQuery(Query query, User user)
			throws SpringbootAppException {
		return this.getPagerModelByQuery(user, query,"UserXML.getPagerModelByQuery");
	}

	@Override
	public List<User> getAll(User user) throws SpringbootAppException {
		return selectList("UserXML.getAll", user);
	}

}
