package com.yaof.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yaof.pojo.Student;
import com.yaof.pojo.User;
import com.yaof.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	@Value("${lockString}")
	private String lockString;	//配置properties属性值注入
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private User user;

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, String param) {
		logger.info("invoke method test, the url is: /test/test" + lockString);
		logger.info("request:"+request);
		logger.info(JSON.toJSONString(user));
		
		System.out.println(testService.getTestString());
		
		return "test";
	}
	
	@RequestMapping("/test02")
	@ResponseBody
	public String test02(HttpServletRequest request) {
		logger.info("invoke method test, the url is: /test/test" + lockString);
		logger.info("request:"+request);
		
		System.out.println(testService.getTestString());
		
		return "test";
	}
	
	@RequestMapping("/testForMuchDatas")
	@ResponseBody
	public List<Object> testForMuchDatas(HttpServletRequest request) {
		List<Object> datas = testService.getAllStudent();
		
		return datas;
	}
	
	@RequestMapping("/insertStudent")
	@ResponseBody
	public String insertStudent(HttpServletRequest request) {
		Student student = new Student();
		student.setStuName("哈哈");
		student.setStuAge("326");
		student.setStuGrade("23");
		student.setStuNumber("11101020111");
		student.setStuSex("1");
		testService.insertStudent(student);
		return "successful";
	}
	
}
