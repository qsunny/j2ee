package com.aaron.springbootDemo.web.controller;

import com.aaron.springbootDemo.beans.log.TeamWorkSearchLog;
import com.aaron.springbootDemo.core.service.log.ITeamWorkSearchLogService;
import com.allchips.common.base.GenericResp;
import com.allchips.common.base.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Resource
	private ITeamWorkSearchLogService teamWorkSearchLogService;

	/**
	 * @param page
	 */
	@ResponseBody
	@RequestMapping(value = "/getSearchLogTest" , method = RequestMethod.POST)
	public GenericResp<Page<TeamWorkSearchLog>> testgetWordListTest(@RequestBody Page<TeamWorkSearchLog> page) {
		GenericResp<Page<TeamWorkSearchLog>> returnVo = new GenericResp<>();
		try {
			page = this.teamWorkSearchLogService.getPagerModelByQuery(page.getParam(),page);
			returnVo.setData(page);

		}catch (Exception e) {
			logger.error("getSearchLogTest异常",e);
			returnVo.setMessage(e.getMessage());
		}
		return returnVo;
	}

	@ResponseBody
	@RequestMapping(value = "/batchInsertTeamWorkSearchLog" , method = RequestMethod.POST)
	public GenericResp<List<TeamWorkSearchLog>> batchInsertTeamWorkSearchLog(@RequestBody List<TeamWorkSearchLog> teamWorkSearchLogList) {
		GenericResp<List<TeamWorkSearchLog>> returnVo = new GenericResp<>();
		try {
			this.teamWorkSearchLogService.batchInsertTeamWorkSearchLog(teamWorkSearchLogList);
			returnVo.setData(teamWorkSearchLogList);

		}catch (Exception e) {
			logger.error("batchInsertTeamWorkSearchLog异常",e);
			returnVo.setMessage(e.getMessage());
		}
		return returnVo;
	}

	@ResponseBody
	@RequestMapping(value = "/analyse" , method = RequestMethod.POST)
	public GenericResp<String> analyse(@RequestBody Map<String,String> param) {
		GenericResp<String> returnVo = new GenericResp<>();
		try {

			return returnVo;

		}catch (Exception e) {
			logger.error("analyse异常",e);
			returnVo.setMessage(e.getMessage());
		}
		return returnVo;
	}

}
