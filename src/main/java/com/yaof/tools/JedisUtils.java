package com.yaof.tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JedisUtils {

    private static Lock lock = new ReentrantLock();

    /**
     * 获取redis缓存数据，如果缓存数据过期了，再去数据库获取数据
     *
     * @param key
     * @return
     */
    private static String getCacheDatas(String key) {
        String result = "取缓存数据";
        if (result == null) {
            if (lock.tryLock()) {
                try {
                    result = "数据库取数据";
                    if (result != null) {
                        System.out.println("设置缓存");
                    }
                } finally {
                    lock.unlock();
                }

            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getCacheDatas(key);
            }
        }


        return "";
    }


}
