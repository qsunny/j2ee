package com.aaron.springweb.core.service.impl;

import com.aaron.springweb.bean.Page;
import com.aaron.springweb.bean.Ping;
import com.aaron.springweb.core.dao.IPingDao;
import com.aaron.springweb.core.service.IPingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public List<Ping> findAllPings() {
		return pingDao.findAllPings();
	}

	@Override
	public Page<Ping> getAllPingByPage(Page<Ping> page) {
		return pingDao.getAllPingByPage(page);
	}

}
