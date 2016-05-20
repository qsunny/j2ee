package com.aaron.search.web.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.aaron.search.core.service.IElasticSearchClientService;
import com.aaron.search.core.service.impl.ElasticSearchClientServiceImpl;

@Configuration
@ComponentScan(basePackages = {
"com.aaron.search.core" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
		"com.aaron.search.web.*" }))
public class AppConfig {
	
	private static final Logger log = Logger.getLogger(AppConfig.class);
	
//	@Autowired
//	private IElasticSearchClientService elasticSearchClientService;
	
	@Bean(initMethod="init",destroyMethod="cleanUp",name="elasticSearchClientService")
	public IElasticSearchClientService elasticSearchClientService() {
		return new ElasticSearchClientServiceImpl();
	}
}
