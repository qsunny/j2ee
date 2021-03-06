package com.aaron.springbootDemo.core.service.log;

import com.aaron.springbootDemo.beans.log.TeamWorkSearchLog;
import com.allchips.common.base.page.Page;

import java.util.List;

/**
* <p>ITeamWorkSearchLogService接口</p>
* <p> aaron.qiu </p>
* <p>Version:1.0.0</p>
* <p>Create Date:2019-10-23 10:25:21 </p>
* <p>Copyright (c) 2017 ~ 2020 </p>
*/
public interface ITeamWorkSearchLogService {

	/**
	* 根据id查询TeamWorkSearchLog对象
	*@param id
	*@return 返回TeamWorkSearchLog
	*/
	public TeamWorkSearchLog getTeamWorkSearchLogById(Long id) throws Exception;

	/**
	* 根据条件查询TeamWorkSearchLog列表
	* @param teamWorkSearchLog
	* @return 返回TeamWorkSearchLog列表
	*/
	public List<TeamWorkSearchLog> getAll(TeamWorkSearchLog teamWorkSearchLog) throws Exception;

	/**
	* 根据条件分页查询TeamWorkSearchLog列表
	* @param teamWorkSearchLog
	* @param page
	* @return 返回TeamWorkSearchLog列表
	*/
	public Page<TeamWorkSearchLog> getPagerModelByQuery(TeamWorkSearchLog teamWorkSearchLog, Page<TeamWorkSearchLog> page) throws Exception;

	/**
	* 添加TeamWorkSearchLog对象
	* @param teamWorkSearchLog
	* @return 返回添加成功的记录条数
	*/
	public int insertTeamWorkSearchLog(TeamWorkSearchLog teamWorkSearchLog) throws Exception;

	/**
	 * @param teamWorkSearchLogList
	 * @return
	 * @throws Exception
	 */
	public List<TeamWorkSearchLog> batchInsertTeamWorkSearchLog(List<TeamWorkSearchLog> teamWorkSearchLogList) throws Exception;

	/**
	* 根据id删除TeamWorkSearchLog对象
	* @param id
	* @return 返回添加成功的记录条数
	*/
	public int delTeamWorkSearchLogById(String id) throws Exception;

	/**
	* 根据TeamWorkSearchLogid删除TeamWorkSearchLog对象
	* @param teamWorkSearchLog
	* @return 返回修改TeamWorkSearchLog成功的记录条数
	*/
	public int updateTeamWorkSearchLog(TeamWorkSearchLog teamWorkSearchLog) throws Exception;
}
