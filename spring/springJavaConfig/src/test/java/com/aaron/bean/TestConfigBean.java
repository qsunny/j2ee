package com.aaron.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aaron.bean.config.AppConfig;
import com.aaron.bean.config.HelloConfig;
import com.aaron.service.GoodbyeService;
import com.aaron.service.HelloWorldService;

public class TestConfigBean {
	
	private ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBeans() {
		HelloWorldService hws = context.getBean(HelloWorldService.class);
		hws.sayHello("Hello Spring 4.2.4");
		hws.printConfig();
		
		GoodbyeService gbs = context.getBean(GoodbyeService.class);
		gbs.sayGoodbye("Goodbye Spring 4.2.4");
		
		HelloConfig hc = context.getBean(HelloConfig.class);
		assertNotNull(hc);
		hc.printConfig();
	}
	

}
