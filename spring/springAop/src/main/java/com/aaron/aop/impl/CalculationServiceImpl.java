package com.aaron.aop.impl;

import org.springframework.stereotype.Service;

import com.aaron.aop.CalculationService;

@Service
public class CalculationServiceImpl implements CalculationService{

	public int add(int a, int b) {
		System.out.println(a + " add " + b);
		return a+b;
	}

	public int div(int a, int b) {
		int result=0;
		try {
			System.out.println(a + " div " + b);
			return result = a/b;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
