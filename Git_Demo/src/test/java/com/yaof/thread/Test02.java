package com.yaof.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test02 {

	private static final Lock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		lock.lock();
		Thread tt = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try
                {
                    lock.lockInterruptibly();
                }
                catch(InterruptedException e)
                {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
			}
		});
		
		Thread.sleep(1000);
		tt.start();
		
		tt.interrupt();
		tt.sleep(10000000);
		

	}

}
