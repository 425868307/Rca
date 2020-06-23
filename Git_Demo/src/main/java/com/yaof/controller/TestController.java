package com.yaof.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaof.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	@ResponseBody
	public String test(String param) {
		logger.info("invoke method test, the url is: /test/test");
		
		System.out.println(testService.getTestString());
		
		return "test";
	}
}
