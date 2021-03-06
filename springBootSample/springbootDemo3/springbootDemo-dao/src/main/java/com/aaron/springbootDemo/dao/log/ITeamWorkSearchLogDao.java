package com.aaron.springbootDemo.dao.log;

import com.aaron.springbootDemo.beans.log.TeamWorkSearchLog;
import com.allchips.common.base.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* <p>ITeamWorkSearchLogDao接口</p>
* <p> aaron.qiu </p>
* <p>Version:1.0.0</p>
* <p>Create Date:2019-10-23 10:25:21 </p>
* <p>Copyright (c) 2017 ~ 2020 Allchips版权所有</p>
*/
@Repository
public interface ITeamWorkSearchLogDao {

   /**
   * 根据id查询TeamWorkSearchLog对象
   * @param id
   * @return 返回TeamWorkSearchLog
   * @throws Exception
   */
   public TeamWorkSearchLog getTeamWorkSearchLogById(Long id) throws Exception;

   /**
    * 根据条件查询TeamWorkSearchLog列表
    * @param teamWorkSearchLog
    * @return 返回TeamWorkSearchLog列表
    * @throws Exception
    */
   public List<TeamWorkSearchLog> getAll(TeamWorkSearchLog teamWorkSearchLog) throws Exception;

   /**
   * 根据条件分页查询TeamWorkSearchLog列表
   * @param teamWorkSearchLog
   * @param page
   * @return 返回TeamWorkSearchLog列表
   * @throws Exception
   */
   public List<TeamWorkSearchLog> getPagerModelByQuery(@Param("teamWorkSearchLog") TeamWorkSearchLog teamWorkSearchLog, @Param("page") Page<TeamWorkSearchLog> page) throws Exception;

   /**
    * 添加TeamWorkSearchLog对象
    * @param teamWorkSearchLog
    * @return 返回添加成功的记录条数
    * @throws Exception
    */
   public int insertTeamWorkSearchLog(TeamWorkSearchLog teamWorkSearchLog) throws Exception;

   /**
   * 根据id删除TeamWorkSearchLog对象
   * @param id
   * @return 返回添加成功的记录条数
   * @throws Exception
   */
   public int delTeamWorkSearchLogById(String id) throws Exception;

   /**
   * 根据TeamWorkSearchLogid修改TeamWorkSearchLog对象
   * @param teamWorkSearchLog
   * @return 返回修改TeamWorkSearchLog成功的记录条数
   * @throws Exception
   */
   public int updateTeamWorkSearchLog(TeamWorkSearchLog teamWorkSearchLog) throws Exception;

    /**
     * @param teamWorkSearchLogList
     * @return
     */
    public Integer batchInsertTeamWorkSearchLog(List<TeamWorkSearchLog> teamWorkSearchLogList) throws Exception;
}
