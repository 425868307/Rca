package com.yaof.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test01 {

	public static void main(String[] args) {
		MyCallable myCallable = new MyCallable();
		FutureTask<String> ft = new FutureTask<>(myCallable);
		Thread thread = new Thread(ft);
		thread.start();
		
		System.out.println("thread started!!!");
		String result = "123";
		try {
			result = ft.get(5000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("get result:"+result);
	}

	
	public static class MyCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			try {
				Thread.sleep(7000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("MyCallable over!");
			return "myCallReturn!!!";
		}
		
	}
}
