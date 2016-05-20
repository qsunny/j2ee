package com.aaron.search.core.service;

import org.elasticsearch.client.Client;

/**
 * Elastic Search Client
 * @author 	Aaaron.Qiu
 * @since	2016-05-15
 */
public interface IElasticSearchClientService {
	
	/**
	 * 初始化客户端资源
	 */
	public void init() throws Exception;
	
	/**
	 * 获取客户端
	 * @return
	 */
	public Client getEsClient();
	
	/**
	 * 释放客户端资源
	 */
	public void cleanUp();
}
