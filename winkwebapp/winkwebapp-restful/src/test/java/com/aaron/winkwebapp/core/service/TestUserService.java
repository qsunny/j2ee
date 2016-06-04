package com.aaron.winkwebapp.core.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aaron.winkwebapp.api.bean.User;
import com.aaron.winkwebapp.api.exception.UserException;
import com.aaron.winkwebapp.restful.config.PersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PersistenceConfig.class})
//@Sql("/wink-init.sql")
//@Transactional
@Ignore
public class TestUserService extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private IUserService userService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback
	public void testAddUser() throws UserException {
		User u = new User();
		u.setUsername("zhangwuji3");
		u.setPassword("888888");
		assertEquals(1, userService.addUser(u));
	}
	
	@Test
	@Commit
	public void testDelUser() throws UserException {
		User u = new User();
		u.setId(1);
		u.setUsername("aaron");
		u.setPassword("888888");
		assertEquals(1, userService.removeUser(u));
	}
	
	@Test
	public void testUserList() {
		userService.getUserList().forEach(u->{
			System.out.println(u);
		});
	}
	
	@Test
	public void testGetUserById() throws UserException {
		User u = userService.getUserById(5);
		System.out.println(u);
		Assert.assertNotNull(u);
	}

}
