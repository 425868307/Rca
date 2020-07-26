package com.yaof.service.impl;

import java.util.List;

import org.apache.ibatis.cache.TransactionalCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.yaof.mapper.TestMapping;
import com.yaof.pojo.Student;
import com.yaof.service.TestService;

@Service
@EnableCaching
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapping testMapping;
	
	@Override
	public String getTestString() {
		System.out.println(testMapping.getCount());
		return "test service result";
	}

	@Override
	public List<Object> getAllStudent() {
		
		return testMapping.getAllStudent();
	}
	
	/**
	 * @CacheEvict这个注解会将缓存删除
	 */
	@Override
	public void insertStudent(Student student) {
		testMapping.insertStudent(student);
	}

}
