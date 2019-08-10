package com.aaron.springbootDemo.core.service.user;

import com.aaron.springbootDemo.bean.User;
import com.aaron.springbootDemo.core.pager.Page;

import java.util.List;


/**
* <p>IUserService接口</p>
* <p> aaron.qiu </p>
* <p>Version:1.0.0</p>
* <p>Create Date:2019-08-14 14:47:50 </p>
* <p>Copyright (c) 2017 ~ 2020版权所有</p>
*/
public interface IUserService {

	/**
	* 根据id查询User对象
	*@param id
	*@return 返回User
	*/
	public User getUserById(String id) throws Exception;

	/**
	* 根据条件查询User列表
	* @param user
	* @return 返回User列表
	*/
	public List<User> getAll(User user) throws Exception;

	/**
	* 根据条件分页查询User列表
	* @param user
	* @param page
	* @return 返回User列表
	*/
	public Page<User> getPagerModelByQuery(User user, Page<User> page) throws Exception;

	/**
	* 添加User对象
	* @param user
	* @return 返回添加成功的记录条数
	*/
	public int insertUser(User user) throws Exception;

	/**
	* 根据id删除User对象
	* @param id
	* @return 返回添加成功的记录条数
	*/
	public int delUserById(String id) throws Exception;

	/**
	* 根据Userid删除User对象
	* @param user
	* @return 返回修改User成功的记录条数
	*/
	public int updateUser(User user) throws Exception;
}
