package com.aaron.springweb.core.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aaron.springweb.core.dao.IPingDao;

@Repository
public class PingDao implements IPingDao {

	public static final Log log = LogFactory.getLog(PingDao.class);
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public void insert(String tag) {
		log.info("Inserting Ping tag: " + tag);
        jdbcTemplate.update("INSERT INTO PING(TAG, TS) VALUES(?, ?)", tag, new Date());
	}

	@Override
	public List<Map<String, Object>> findAllPings() {
		return jdbcTemplate.queryForList("SELECT * FROM PING ORDER BY TS");
	}

}
