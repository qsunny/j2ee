package com.aaron.springweb.core.dao.impl;

import com.aaron.springweb.bean.Page;
import com.aaron.springweb.bean.Ping;
import com.aaron.springweb.core.dao.BaseDao;
import com.aaron.springweb.core.dao.IPingDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class PingDao extends BaseDao<Ping> implements IPingDao {

	public static final Log log = LogFactory.getLog(PingDao.class);
	
	@Override
	public void insert(String tag) {
		log.info("Inserting Ping tag: " + tag);
        jdbcTemplate.update("INSERT INTO PING(TAG, TS) VALUES(?, ?)", tag, new Date());
	}

	@Override
	public List<Map<String,Object>> findAllPings() {

		return this.queryForList("SELECT * FROM PING ORDER BY TS",null);
	}

	@Override
	public Page<Ping> getAllPingByPage(Page<Ping> page) {
		String sql = "SELECT * FROM PING ORDER BY TS";
		return this.findBySqlForPage(sql,null,page);
	}


}
