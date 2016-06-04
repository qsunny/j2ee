package com.aaron.winkwebapp.api.bean;

import java.io.Serializable;

import org.apache.wink.common.annotations.Asset;

@SuppressWarnings("serial")
@Asset
public class User implements Serializable {
	
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createtime=" + createtime
				+ "]";
	}

}
