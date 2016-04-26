package com.aaron.bean.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aaron.service.GoodbyeService;
import com.aaron.service.impl.GoodbyeServiceImpl;

@Configuration
public class GoodbyeConfig {
	
	@Resource
	private GoodbyeService goodbyeService;
	
	/*
    @Bean(name="goodbyeBean")
    public GoodbyeService goodByeService() {
        return new GoodbyeServiceImpl();
    }
    */
}
