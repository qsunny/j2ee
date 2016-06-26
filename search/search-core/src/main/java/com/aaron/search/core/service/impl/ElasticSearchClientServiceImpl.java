package com.aaron.search.core.service.impl;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.aaron.search.core.service.IElasticSearchClientService;

public class ElasticSearchClientServiceImpl implements IElasticSearchClientService {
	
	private static final Logger log = Logger.getLogger(ElasticSearchClientServiceImpl.class);
	
	//es client
	public Client client;
	
	@Override
	public void init() throws Exception {
//		Settings settings = Settings.settingsBuilder()
//		        .put("cluster.name", "myClusterName").build();
//		Client client = TransportClient.builder().settings(settings).build();
		if(null==client) {
			client = TransportClient.builder().build()
			        //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.103"), 9300));
		}
	}

	@Override
	public Client getEsClient() {
		return client;
	}

	@Override
	public void cleanUp() {
		client.close();
	}

}
