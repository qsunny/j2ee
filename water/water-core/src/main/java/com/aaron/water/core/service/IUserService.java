package com.aaron.water.core.service;

import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;
import com.aaron.water.bean.User;
import com.aaron.water.exception.WaterException;

import java.util.List;

/**
 * @Description:用户信息相关操作service接口
 * @Author:Aaron.Qiu
 * @Since:2015-04-12
 * @Copyright:Copyright (c)  2014 ~ 2015 版权所有
 */
public interface IUserService {

	/**
	 * @Description:添加用户
	 * @param user 用户实体类
	 * @throws WaterException
	 */
	public int insertUser(User user) throws WaterException;

	/**
	 * @param user
	 * @throws WaterException
	 * @Description:修改用户信息
	 */
	public int updateUser(User user) throws WaterException;

	/**
	 * @Description:根据id删除用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	public int deleteUserById(String userId) throws WaterException;
	
	/**
	 * @Description:根据用户id获取用户信息
	 * @param userId 用户id
	 * @return 
	 * @throws Exception
	 */
	public User getUserById(String userId) throws WaterException;
	
	/**
	 * 
	 * @param query 分页参数
	 * @param user 查询条件对象
	 * @return
	 * @throws Exception
	 * @Description:分页获取用户信息
	 */
	public PagerModel<User> getPagerModelByQuery(Query query,
			User user) throws WaterException;

	/**
	 * @Description:根据条件查询
	 * @param user 查询条件
	 * @return
	 * @throws WaterException
	 */
	public List<User> getAll(User user) throws WaterException;
	
}
