package com.yaof.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value="/test")
	public String test(String param) {
		logger.info("invoke method test, the url is: /test/test");
		
		System.out.println("invoke test");
		return "test";
	}
}
