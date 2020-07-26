package com.yaof.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test03 {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t1 are working");
				try {
					Thread.sleep(3000);
					cdl.countDown();
					System.out.println("t1 work over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t2 are working");
				try {
					Thread.sleep(6000);
					cdl.countDown();
					System.out.println("t2 work over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t3 are working");
				try {
					Thread.sleep(4000);
					cdl.countDown();
					System.out.println("t3 work over");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		ExecutorService ee = Executors.newFixedThreadPool(5);
		
		
		Boss boss = new Boss(cdl);
		ee.execute(boss);
		ee.execute(t1);
		ee.execute(t3);
		ee.execute(t2);
	}
	
	private static CountDownLatch cdl = new CountDownLatch(3);

	public static class Boss implements Runnable{
		CountDownLatch cdl;
		public Boss(CountDownLatch cdl){
			this.cdl = cdl;
		}

		@Override
		public void run() {
			System.out.println("boss are working");
			try {
				cdl.await();
				Thread.sleep(1000);
				System.out.println("boss work over");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
