package com.aaron.springbootDemo.core.service.log.impl;


import com.aaron.springbootDemo.beans.log.TeamWorkSearchLog;
import com.aaron.springbootDemo.core.service.log.ITeamWorkSearchLogService;
import com.aaron.springbootDemo.dao.log.ITeamWorkSearchLogDao;
import com.allchips.common.base.page.Page;
import com.allchips.tools.utils.CommUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* <p>ITeamWorkSearchLogService接口实现类</p>
* <p> aaron.qiu </p>
* <p>Version:1.0.0</p>
* <p>Create Date:2019-10-23 10:25:21 </p>
* <p>Copyright (c) 2017 ~ 2020 </p>
*/
@Service
public class TeamWorkSearchLogServiceImpl implements ITeamWorkSearchLogService {
	@Resource
	private ITeamWorkSearchLogDao teamWorkSearchLogDao;

	@Transactional(propagation= Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public TeamWorkSearchLog getTeamWorkSearchLogById(Long id) throws Exception {
		return this.teamWorkSearchLogDao.getTeamWorkSearchLogById(id);
	}

	@Transactional(propagation= Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public List<TeamWorkSearchLog> getAll(TeamWorkSearchLog teamWorkSearchLog) throws Exception {
		return this.teamWorkSearchLogDao.getAll(teamWorkSearchLog);
	}

	@Transactional(propagation= Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public Page<TeamWorkSearchLog> getPagerModelByQuery(TeamWorkSearchLog teamWorkSearchLog, Page<TeamWorkSearchLog> page)
			throws Exception {
		List<TeamWorkSearchLog> result = this.teamWorkSearchLogDao.getPagerModelByQuery(teamWorkSearchLog, page);
		page.setResult(result);
		return page;
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int insertTeamWorkSearchLog(TeamWorkSearchLog teamWorkSearchLog) throws Exception {
		if(CommUtils.isNull(teamWorkSearchLog)) return 0;
		teamWorkSearchLog.setCreateTime(new Date());
		teamWorkSearchLog.setUpdateTime(new Date());
		return this.teamWorkSearchLogDao.insertTeamWorkSearchLog(teamWorkSearchLog);
	}

	@Transactional(propagation = Propagation.REQUIRED,
			rollbackFor = Exception.class
	)
	@Override
	public List<TeamWorkSearchLog> batchInsertTeamWorkSearchLog(List<TeamWorkSearchLog> teamWorkSearchLogList) throws Exception {
		if (CollectionUtils.isEmpty(teamWorkSearchLogList)) {
			return teamWorkSearchLogList;
		}
		teamWorkSearchLogList.forEach((log)->{
			log.setCreateTime(new Date());
			log.setUpdateTime(new Date());
		});
		teamWorkSearchLogDao.batchInsertTeamWorkSearchLog(teamWorkSearchLogList);
		return teamWorkSearchLogList;
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int delTeamWorkSearchLogById(String id) throws Exception {
		return this.teamWorkSearchLogDao.delTeamWorkSearchLogById(id);
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int updateTeamWorkSearchLog(TeamWorkSearchLog teamWorkSearchLog) throws Exception {
		return this.teamWorkSearchLogDao.updateTeamWorkSearchLog(teamWorkSearchLog);
	}
}

