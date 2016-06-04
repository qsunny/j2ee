package com.aaron.winkwebapp.restful.resources;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aaron.winkwebapp.api.bean.User;
import com.aaron.winkwebapp.restful.config.WebappConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebappConfig.class})
public class TestUserResource extends AbstractJUnit4SpringContextTests{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserById() {
		// create the rest client instance
		RestClient client = new RestClient();
		// create the resource instance to interact with,通过路径参数传递
		Resource resource = client.resource("http://localhost:8080/winkwebapp-restful/rest/users/5");
		// perform a GET on the resource. The resource will be returned as plain text
		User response = resource.get(User.class);
		System.out.println(response);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testGetUserList() {
		// create the rest client instance
		RestClient client = new RestClient();
		// create the resource instance to interact with
		Resource resource = client.resource("http://localhost:8080/winkwebapp-restful/rest/users/getUserList");
		// perform a GET on the resource. The resource will be returned as plain text
		List<User> response = resource.get(List.class);
		System.out.println(response);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testAddUser() {
		// create the rest client instance
		RestClient client = new RestClient();
		// create the resource instance to interact with
		Resource resource = client.resource("http://localhost:8080/winkwebapp-restful/rest/users/addUser");
		// perform a GET on the resource. The resource will be returned as plain text
		User u = new User();
		u.setUsername("小龙女");
		u.setPassword("1111111111");
		JSONObject response = resource.contentType(MediaType.APPLICATION_JSON).post(JSONObject.class,u);
		System.out.println(response);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testUpdateUser() {
		// create the rest client instance
		RestClient client = new RestClient();
		// create the resource instance to interact with
		Resource resource = client.resource("http://localhost:8080/winkwebapp-restful/rest/users/updateUser");
		// perform a GET on the resource. The resource will be returned as plain text
		User u = new User();
		//u.setId(2);
		u.setUsername("杨过");
		u.setPassword("333333");
		JSONObject response = resource.contentType(MediaType.APPLICATION_JSON).put(JSONObject.class,u);
		System.out.println("update=========>" + response);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testDeleteUser() {
		// create the rest client instance
		RestClient client = new RestClient();
		// create the resource instance to interact with
		Resource resource = client.resource("http://localhost:8080/winkwebapp-restful/rest/users/3");
		// perform a GET on the resource. The resource will be returned as plain text
			
		JSONObject response = resource.contentType(MediaType.APPLICATION_JSON).delete(JSONObject.class);
		System.out.println(response);
		Assert.assertNotNull(response);
	}

}
