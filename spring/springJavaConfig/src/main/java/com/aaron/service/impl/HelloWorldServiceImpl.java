package com.aaron.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aaron.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	
	@Value("${db.ip}")
	private String dbip;
	
	@Value("${db.user}")
	private String user;
	
	@Value("${db.pwd}")
	private String pwd;
	
	public HelloWorldServiceImpl() {
	}
	
	public HelloWorldServiceImpl(String dbip,String user,String pwd) {
		this.dbip = dbip;
		this.user = user;
		this.pwd = pwd;
	}
	
	@Override
	public void sayHello(String name) {
		System.out.println("Hello from Java Configuration. " + name);
	}
	
	@Override
	public void printConfig() {
		System.out.println("Hello from Java Configuration dbip: " + dbip);
		System.out.println("Hello from Java Configuration user: " + user);
		System.out.println("Hello from Java Configuration pwd: " + pwd);
	}

}
