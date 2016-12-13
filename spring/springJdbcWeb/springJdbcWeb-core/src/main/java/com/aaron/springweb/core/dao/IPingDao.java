package com.aaron.springweb.core.dao;

import com.aaron.springweb.bean.Page;
import com.aaron.springweb.bean.Ping;

import java.util.List;
import java.util.Map;

public interface IPingDao {

	public void insert(String tag);

	public List<Map<String,Object>> findAllPings();

	public Page<Ping> getAllPingByPage(Page<Ping> page);
	
}
