package com.yaof.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaof.aop.MyAnno;
import com.yaof.mapper.TestMapping;
import com.yaof.pojo.Student;
import com.yaof.service.TestService;

@Service
@EnableCaching
public class TestServiceImpl implements TestService {
	
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private TestMapping testMapping;
	
	@Override
	@MyAnno("myAnno,getTestString")
	public String getTestString() {
		logger.info("查询数据库student表数据总条数："+testMapping.getCount());
//		ApplicationContextUtil.getBean(this.getClass()).getAllStudent();	//如果再拿到一次类，并调用相应的方法，会再次触发相应的方法的切入方法
		return "test service result";
	}

	@Override
	public List<Object> getAllStudent() {
		logger.info("getAllStudent-service-method-invoke------------");
		return testMapping.getAllStudent();
	}
	
	/**
	 * @throws Exception 
	 * @CacheEvict这个注解会将缓存删除
	 */
	@Override
	@Transactional	//@Transactional实现事务，方法中当抛出error或者runtimeException时，会回滚事务
	public void insertStudent(Student student) throws Exception {
		testMapping.insertStudent(student);
		throw new RuntimeException();
	}

}
