package com.aaron.aop;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCalculationService {
	private ApplicationContext applicationContext;
	private CalculationService calculationService;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		calculationService = applicationContext.getBean(CalculationService.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculation() {
		calculationService.add(1, 3);
		
		calculationService.div(10, 0);
	}

}
