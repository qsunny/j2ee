package com.aaron.service.impl;

import org.springframework.stereotype.Service;

import com.aaron.service.GoodbyeService;

@Service
public class GoodbyeServiceImpl implements GoodbyeService {

	@Override
	public void sayGoodbye(String name) {
		System.out.println("Goodbye from Java Configuration. "+ name);
	}

}
