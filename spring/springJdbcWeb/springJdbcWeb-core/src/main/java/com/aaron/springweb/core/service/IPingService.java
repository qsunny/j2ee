package com.aaron.springweb.core.service;

import java.util.List;
import java.util.Map;

public interface IPingService {
	
	public void insert(String tag);
	
	public List<Map<String, Object>> findAllPings();
}
