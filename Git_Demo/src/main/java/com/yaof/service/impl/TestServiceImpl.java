package com.yaof.service.impl;

import org.springframework.stereotype.Service;

import com.yaof.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public String getTestString() {
		return "test service result";
	}

}
