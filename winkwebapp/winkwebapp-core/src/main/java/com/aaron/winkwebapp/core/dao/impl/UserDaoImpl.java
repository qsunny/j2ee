package com.aaron.winkwebapp.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aaron.winkwebapp.api.bean.User;
import com.aaron.winkwebapp.api.exception.UserException;
import com.aaron.winkwebapp.core.dao.BaseDao;
import com.aaron.winkwebapp.core.dao.IUserDao;

@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {
	
	public static final Log log = LogFactory.getLog(UserDaoImpl.class);
	
	@Override
	public int addUser(User user) throws UserException {
		if(null==user) throw new UserException(user);
		log.info("Inserting User: " + user);
        return jdbcTemplate.update("INSERT INTO tbl_user(username, password,createtime) VALUES(?, ?, ?)",user.getUsername(),user.getPassword(),new Date());
	}

	@Override
	public int removeUser(User user) throws UserException {
		if(null==user || null==user.getId()) throw new UserException(user);
		log.info("Deleting User: " + user);
        return jdbcTemplate.update("delete from tbl_user where id=?",user.getId());
	}

	@Override
	public int updateUser(User user) throws UserException {
		if(null==user || null==user.getId()) throw new UserException(user);
		log.info("Updating User: " + user);
        return jdbcTemplate.update("update tbl_user set username=?,password=? where id=?",user.getUsername(),user.getPassword(),user.getId());
	}

	@Override
	public User getUserById(int id) throws UserException {
		String sql = "select * from tbl_user where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id},new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rownum) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setCreatetime(rs.getString("createtime"));
				return u;
			}
		});
	}

	@Override
	public List<User> getUserList() {
		String sql = "select id,username,password,createtime createTime from tbl_user";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
				
		return jdbcTemplate.query(sql, rowMapper, new Object[]{});
	}
	
	

}
