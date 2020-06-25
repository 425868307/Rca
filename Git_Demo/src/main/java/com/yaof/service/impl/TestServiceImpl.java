package com.yaof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaof.mapper.TestMapping;
import com.yaof.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapping testMapping;
	
	@Override
	public String getTestString() {
		System.out.println(testMapping.getCount());
		return "test service result";
	}

}
