package com.yaof.mq;

import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * 消息生存者
 * @author yaofang
 *
 */
public class Test01 {

	 public static void main(String[] args) throws JMSException {

	    //创建连接工厂，这个参数就是自己的activeMQ的地址
	    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

	    //2.创建连接
	    Connection connection = connectionFactory.createConnection();

	    //3.启动连接
	    connection.start();

	    //4.获取session(会话对象)
	    /*
	    arg0 是否启用事务
	    arg1 消息的确认方式 自动确认
	     */
	    Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

	    //5.创建一个队列对象,名称
	    Queue firstQueue = session.createQueue("firstQueue");

	    //6.创建一个消息的生产者对象
//		    Destination destination = ;//目标对象
	    MessageProducer producer = session.createProducer(firstQueue);

	    //7.创建一个消息
//	    TextMessage textMessage = session.createTextMessage("你好，我是姚放！！！！");
	    HashMap<String, Object> hashMap = new HashMap<>();
	    hashMap.put("dsa", 8732);
	    hashMap.put("ewq", 1223);
	    hashMap.put("few", 16516);
	    
	    User user = new User();
	    user.setId(432432);
	    user.setName("yaoewqewqfang");
	    user.setAddress("中国");
	    
	    ObjectMessage objectMessage = session.createObjectMessage(user);
	    
	    
	    //8.发送消息
	    producer.send(objectMessage);
	    //9.关闭资源
	    producer.close();
	    session.close();
	    connection.close();

	  }
}
