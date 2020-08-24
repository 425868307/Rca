package com.yaof.service;

import java.util.List;

import com.yaof.pojo.Student;

public interface TestService {

	public String getTestString();
	
	public List<Object> getAllStudent();
	
	public void insertStudent(Student student) throws Exception;
}
