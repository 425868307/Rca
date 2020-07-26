package com.yaof.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test03 {
	
	static{
		System.out.println("static 11111");
	}
	{
		System.out.println("11111");
	}
	
	public Test03(){
		System.out.println("dsadadsad");
	}

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		try {
			Class.forName("com.yaof.test.Test03");
			ClassLoader.getSystemClassLoader().loadClass("com.yaof.test.Test03");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
