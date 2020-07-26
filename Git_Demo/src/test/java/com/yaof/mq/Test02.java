package com.yaof.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.alibaba.fastjson.JSON;

/**
 * 消息消费者
 * @author yaofang
 *
 */
public class Test02 {

	public static void main(String[] args) {

		ActiveMQConnectionFactory connectionFaction = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		connectionFaction.setTrustAllPackages(true);
		
		try {
			Connection connection = connectionFaction.createConnection();
			connection.start();

			//4.获取session(会话对象)
		    /*
		    arg0 是否启用事务
		    arg1 消息的确认方式 自动确认
		     */
		    Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		    
		    
		    Queue queue = session.createQueue("firstQueue");
			
		    MessageConsumer consumer = session.createConsumer(queue);
		    
//		    ObjectMessage receive = (ObjectMessage)consumer.receive();
//		    
//		    System.out.println(JSON.toJSONString(receive.getObject()));
		    
		    consumer.setMessageListener(new MessageListener(){

				@Override
				public void onMessage(Message message) {
					try {
						if(message instanceof TextMessage) {
							System.out.println("提取的消息是"+((TextMessage) message).getText());
						}
						if(message instanceof ObjectMessage){
							ObjectMessage om = (ObjectMessage) message;
							System.out.println("提取的消息是"+JSON.toJSONString(om.getObject()));
						}
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
		    });
			
		    //8.等待键盘输入
		    //目的是为了让程序停止来看效果
		    System.in.read();
		 
		 
		    //9.关闭资源
		    consumer.close();
		    session.close();
		    connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
