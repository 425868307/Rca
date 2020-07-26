package com.yaof.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yaof.pojo.User;



/**
 * 序列化&反序列化相关的方法
 * @author yaofang
 *
 */
public class SerializeUtils {

	private static final Logger logger = LoggerFactory.getLogger(SerializeUtils.class);
	
	/**
	 * 将对象序列化成为字节数组  【使用Java自带的序列化方法进行序列化】
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static byte[] getSerializeStringByObject(Serializable obj) throws Exception{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		byte[] result = bos.toByteArray();
		oos.close();
		bos.close();
		return result;
	}
	
	/**
	 * 将字节数组反序列化成对象
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectByString(byte[] str) throws Exception{
		ByteArrayInputStream bis = new ByteArrayInputStream(str);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object result = ois.readObject();
		ois.close();
		bis.close();
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setAddress("中国上海市");
//		byte[] serString = getSerializeStringByObject(user);
//		
//		JedisUtil.getJedis().set("get * from user".getBytes(), serString);
		
		
		byte[] bs = JedisUtil.getJedis().get("get * from user".getBytes());
		Object obj = getObjectByString(bs);
		System.out.println(JSON.toJSONString(obj));
		
	}
}
