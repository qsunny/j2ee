package com.aaron.water.web.test;

import com.aaron.water.core.service.IUserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/commons.xml"})
public class TestConfig {

	@Resource
	private IUserService userService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

		
//	@Test
//	public void testConfigResource() {
//		 ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
//	                new String []{"/commons.xml"});
//		 User u = ctx.getBean("user", User.class);
//	}
	
	@Test
	public void testGetBean() {
		Assert.assertNotNull(userService);
	}

}
