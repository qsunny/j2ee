package com.aaron.springweb.core.dao;

import java.util.List;
import java.util.Map;

public interface IPingDao {

	public void insert(String tag);
	
	public List<Map<String, Object>> findAllPings();
	
}
