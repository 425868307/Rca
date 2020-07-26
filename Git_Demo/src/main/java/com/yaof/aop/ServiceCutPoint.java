package com.yaof.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServiceCutPoint {
	
	@Autowired(required=true)
	private HttpServletRequest request;
	

	@Before("execution(* com.yaof.service.**.*(..))")
	public void before(){
		System.out.println("dsaaaaaaaaaaaaaaaaaaaaaaaa");
	}
}
