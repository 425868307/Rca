package com.yaof.util;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.util.StringUtils;

public class JedisCacheUtil {

	private static ReentrantLock lock = new ReentrantLock();
	
	public static String getFormDatas(String key){
		String cache = JedisUtil.getJedis().get(key);
		
		if(StringUtils.isEmpty(cache)){
			if(lock.tryLock()){
				cache = "数据库取到的数据";
				JedisUtil.getJedis().set(key, cache);
			} else {
				try {
					Thread.sleep(100);
					cache = getFormDatas(key);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return cache;
	}
}
