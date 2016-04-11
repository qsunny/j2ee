package com.aaron.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//数值越小优先级超高,执行的优先级
@Order(1)
@Aspect
@Component
public class LogAspect {
	
	@Pointcut("execution(* com.aaron.aop.impl.CalculationServiceImpl.*(..))")
	public void joinPointExpression() {}
	
	/*	
 	@Before("execution(* com.aaron.aop.impl.CalculationServiceImpl.*(..))")
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		
		System.out.println("The method  " + methodName + " begin args " + Arrays.asList(args));
	}
	*/
	
	@Before("joinPointExpression()")
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		
		System.out.println("The method  " + methodName + " begin args " + Arrays.asList(args));
	}
	
	@After("joinPointExpression()")
	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		
		System.out.println("The method  " + methodName + " ends args " + Arrays.asList(args));
	}
	
	@AfterReturning(value="joinPointExpression()",returning="result")
	public void afterReturningMethod(JoinPoint joinpoint,Object result) {
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		
		System.out.println("The method  " + methodName + " after returning args " + Arrays.asList(args) + " result " +result);
	}
	
	@AfterThrowing(value="joinPointExpression()",throwing="ex")
	public void afterThrowgMethod(JoinPoint joinpoint,Exception ex) {
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		
		System.out.println("The method  " + methodName + " occur exception "+ ex);
	}
	
}
