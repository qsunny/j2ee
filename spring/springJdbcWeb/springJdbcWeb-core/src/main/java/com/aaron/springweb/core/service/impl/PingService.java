package com.aaron.springweb.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aaron.springweb.core.dao.IPingDao;
import com.aaron.springweb.core.service.IPingService;

@Service
public class PingService implements IPingService {
	
	@Autowired
	private IPingDao pingDao;
	
	@Transactional(propagation=Propagation.REQUIRED,
			rollbackFor=java.lang.Exception.class
			)
	@Override
	public void insert(String tag) {
		pingDao.insert(tag);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
			isolation=Isolation.READ_COMMITTED,
			readOnly=true)
	@Override
	public List<Map<String, Object>> findAllPings() {
		return pingDao.findAllPings();
	}

}
