package com.yaof.service;

import java.util.List;

import com.yaof.pojo.Student;

public interface TestService {

	String getTestString();
	
	List<Object> getAllStudent();
	
	void insertStudent(Student student) throws Exception;
}
