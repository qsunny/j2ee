package com.aaron.waterspringboot.core.service;

import java.util.List;

import com.aaron.waterspringboot.bean.User;
import com.aaron.waterspringboot.exception.WaterSpringbootException;
import com.cecport.tools.pager.PagerModel;
import com.cecport.tools.pager.Query;

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
	 * @throws UcenterException
	 */
	public int insertUser(User user) throws WaterSpringbootException;

	/**
	 * @param user
	 * @throws UcenterException
	 * @Description:修改用户信息
	 */
	public int updateUser(User user) throws WaterSpringbootException;

	/**
	 * @Description:根据id删除用户
	 * @param id 用户id
	 * @throws Exception
	 */
	public int deleteUserById(String userId) throws WaterSpringbootException;
	
	/**
	 * @Description:根据用户id获取用户信息
	 * @param userId 用户id
	 * @return 
	 * @throws Exception
	 */
	public User getUserById(String userId) throws WaterSpringbootException;
	
	/**
	 * 
	 * @param query 分页参数
	 * @param user 查询条件对象
	 * @return
	 * @throws Exception
	 * @Description:分页获取用户信息
	 */
	public PagerModel<User> getPagerModelByQuery(Query query,
			User user) throws WaterSpringbootException;

	/**
	 * @Description:根据条件查询
	 * @param orderAddress 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<User> getAll(User user) throws WaterSpringbootException;
	
}
