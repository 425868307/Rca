package com.yaof.util;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class JedisUtil {
	
	private static Jedis jedis;
	
	/**
	 * 获取jedis对象
	 * @return
	 */
	public static Jedis getJedis(){
		synchronized (JedisUtil.class) {
			if(jedis == null) {
				synchronized (JedisUtil.class) {
					jedis = new Jedis("localhost", 6379);
				}
			}
		}
		return jedis;
	}
	
	/**
	 * 获取client对象
	 * @return
	 */
	public static Client getClient(){
		return getJedis().getClient();
	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379); // 连接Redis
        int i = 0;
        try {
            long start = System.currentTimeMillis(); // 开始毫秒数
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    // 当大于等于1000毫秒（相当于1秒）时，结束操作
                    break;
                }
                i++;
                jedis.set("test" + i, i + "vlu", SetParams.setParams().ex(30));
            }
        } finally {
            // 关闭连接
            jedis.close();
        }
        // 打印1秒内对Redis的操作次数
        System.out.println("reids每秒操作：" + i + "次");
	}

}
