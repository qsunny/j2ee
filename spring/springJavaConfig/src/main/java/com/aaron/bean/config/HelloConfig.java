package com.aaron.bean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.aaron.service.HelloWorldService;

@Configuration
@ImportResource("classpath*:applicationContext.xml")
@PropertySource("classpath:application.properties")
public class HelloConfig {
	
	@Autowired
    Environment env;
	
	@Value("${db.ip}")
    private String dbip;

    @Value("${db.user}")
    private String user;

    @Value("${db.pwd}")
    private String pwd;
	
	@Autowired
	public HelloWorldService helloWorldService;
	
	public void printConfig() {
		System.out.println(" dbip: " + dbip);
		System.out.println(" user: " + user);
		System.out.println(" pwd: " + pwd);
	}
	
	
	
	/**
	@Bean(name="helloWorldBean")
     public HelloWorldService helloWorldService() {
         return new HelloWorldServiceImpl();
     }
     */

}
