package com.aaron.springbootApp.core.dao;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.exception.SpringbootAppException;
import com.aaron.tools.pager.PagerModel;
import com.aaron.tools.pager.Query;

import java.util.List;


/**
 * @Description:用户信息相关操作接口
 * @Author:Aaron.Qiu
 * @Since:2016-01-09
 * @Copyright:Copyright (c)  2016 ~ 2017 版权所有
 */
public interface IUserDao {

	/**
	 * @Description:添加用户
	 * @param user 用户实体类
	 * @throws SpringbootAppException
	 */
	public int insertUser(User user) throws SpringbootAppException;

	/**
	 * @param user
	 * @throws SpringbootAppException
	 * @Description:修改用户信息
	 */
	public int updateUser(User user) throws SpringbootAppException;

	/**
	 * @Description:根据id删除用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	public int deleteUserById(String userId) throws SpringbootAppException;
	
	/**
	 * @Description:根据用户id获取用户信息
	 * @param userId 用户id
	 * @return 
	 * @throws Exception
	 */
	public User getUserById(String userId) throws SpringbootAppException;
	
	/**
	 * 
	 * @param query 分页参数
	 * @param user 查询条件对象
	 * @return
	 * @throws Exception
	 * @Description:分页获取用户信息
	 */
	public PagerModel<User> getPagerModelByQuery(Query query,
												 User user) throws SpringbootAppException;

	/**
	 * @Description:根据条件查询
	 * @param user 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<User> getAll(User user) throws SpringbootAppException;
	
    
}
