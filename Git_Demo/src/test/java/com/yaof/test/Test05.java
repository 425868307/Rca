package com.yaof.test;

import com.yaof.io.User;

public class Test05 {

	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader.getSystemClassLoader().loadClass("com.yaof.test.Test03");
//		Class.forName("com.yaof.test.Test03");
		User user = new User();
		user.setName("yaofffff");
		System.out.println(user);
		user.setName("dcs犯得上发生");
		user.setAge(23);
		System.out.println(user);
	}

}
