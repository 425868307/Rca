package com.yaof.test;

import java.util.concurrent.ArrayBlockingQueue;

public class Test02 {

	public static void main(String[] args) {

		try {
			Class.forName("com.yaof.test.Test01");
//			Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass("com.yaof.test.Test01");
//			loadClass.newInstance();
			
			ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(10, false);
			abq.put("1");
			Thread.sleep(1000);
			abq.put("2");
			Thread.sleep(1000);
			abq.put("3");
			Thread.sleep(1000);
			abq.put("4");
			Thread.sleep(1000);
			
			abq.put("5");
			Thread.sleep(1000);
			abq.put("6");
			Thread.sleep(1000);
			
			for (int i = abq.size(); i > 0; i--) {
				
				System.out.println(abq.poll());
				Thread.sleep(1500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
