package com.aaron.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aaron.bean.User;
import com.aaron.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> getUserList() {
		//sql列的别名需要跟实体类的属性对应
		String sql = "select * from tbl_user";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		List<User> list = jdbcTemplate.query(sql, rowMapper, new Object[]{});
		return list;
	}

	@Override
	public int updateUser(User u) {
		if(null==u) return 0; 
		
		String sql = "update tbl_user set age=? where username=?";
		int result = jdbcTemplate.update(sql,u.getAge(),u.getUsername());
		//int i = 1/0;
		return result;
	}

}
