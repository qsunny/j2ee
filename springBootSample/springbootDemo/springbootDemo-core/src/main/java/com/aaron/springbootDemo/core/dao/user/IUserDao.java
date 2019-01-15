package com.aaron.springbootDemo.core.dao.user;



import com.aaron.springbootDemo.bean.User;
import com.allchips.common.base.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>IUserDao接口</p>
 * <p> aaron.qiu </p>
 * <p>Version:1.0.0</p>
 * <p>Create Date:2019-01-14 14:47:50 </p>
 * <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
 */
@Repository
public interface IUserDao {

	/**
	* 根据id查询User对象
	* @param id
	* @return 返回User
	* @throws Exception
	*/
	public User getUserById(String id) throws Exception;

	/**
	 * 根据条件查询User列表	 
	 * @param user
     * @return 返回User列表
     * @throws Exception
	 */
	public List<User> getAll(User user) throws Exception;

	/**
	* 根据条件分页查询User列表
	* @param user
	* @param page
	* @return 返回User列表
	* @throws Exception
	*/
	public List<User> getPagerModelByQuery(@Param("user") User user, @Param("page") Page<User> page) throws Exception;

	/**
	 * 添加User对象
	 * @param user
	 * @return 返回添加成功的记录条数
	 * @throws Exception
	 */
	public int insertUser(User user) throws Exception;

	/**
	* 根据id删除User对象
	* @param id
	* @return 返回添加成功的记录条数
	* @throws Exception
	*/
	public int delUserById(String id) throws Exception;

	/**
	* 根据Userid删除User对象
	* @param user
	* @return 返回修改User成功的记录条数
	* @throws Exception
	*/
	public int updateUser(User user) throws Exception;
}
