package org.aaron.jdbtemplate;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.aaron.bean.User;
import com.aaron.service.UserService;
import com.alibaba.druid.pool.DruidDataSource;


public class JdbcTemplateTest {
	
	private ApplicationContext applicationContext;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		namedParameterJdbcTemplate = applicationContext.getBean(NamedParameterJdbcTemplate.class);
		userService = applicationContext.getBean(UserService.class);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testDataSource() {
		DataSource dataSource = applicationContext.getBean(DruidDataSource.class);
		Assert.assertNotNull(dataSource);
	}
	
	@Test
	public void testJdbcTemplate() {
		Assert.assertNotNull(jdbcTemplate);
	}
	
	@Test
	public void testUpdate() {
		String sql = "update tbl_user set age=? where username=?";
		jdbcTemplate.update(sql,30,"andoni");
	}
	
	@Test
	public void testQueryForObject() {
		String sql = "select * from tbl_user where id=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User u = jdbcTemplate.queryForObject(sql, rowMapper,"4028b881521c318301521c3183d20000");
		System.out.println(u);
	}
	
	@Test
	public void testQueryForList() {
		//sql列的别名需要跟实体类的属性对应
//		String sql = "select * from tbl_user";
//		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
//		List<User> list = jdbcTemplate.query(sql, rowMapper, new Object[]{});
		List<User> list = userService.getUserList();
		System.out.println(list);
	}
	
	@Test
	public void testUserServiceUpdate() {
		User u = new User();
		u.setAge(22);
		u.setUsername("Lisa");
		userService.updateUser(u);
	}
	
	@Test
	public void testQueryForCount() {
		String sql = "select count(id) from tbl_user";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	
	@Test
	public void testBatchInsert() {
		String sql = "insert into tbl_user(id,username,age) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[]{"abcddsfdf","kuli",22});
		batchArgs.add(new Object[]{"abcdds23f","Gelin",28});
		batchArgs.add(new Object[]{"abcdd33df","Tompus",29});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	@Test
	public void testNamedParameInsert() {
		String sql = "insert into tbl_user(id,username,age) values(:id,:un,:age)";
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", "sdf343dfeerer");
		paramMap.put("un", "Lucy");
		paramMap.put("age", 25);
		int result = namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	/**
	 * 参数对应于实体类
	 */
	@Test
	public void testNamedParameInsert2() {
		String sql = "insert into tbl_user(id,username,age) values(:id,:username,:age)";
		User u =  new User();
		u.setId("22123fjdkfjdkfjse");
		u.setUsername("Lisa");
		u.setAge(30);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(u);
		int result = namedParameterJdbcTemplate.update(sql, paramSource);
	}
	


}
