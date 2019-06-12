package com.cmdi.util;

import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cmdi.model.CnPoorCellTaskDoneMessage;
import com.cmdi.model.CnTaskDoneMessage;

/**
 * @ClassName: TestG
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2018年11月15日
 * @version: 1.0
 */
@Component
public class ActiveMqClient {
	public static Logger logger = Logger.getLogger(ActiveMqClient.class);
	public static HashMap<String, Boolean> FL = new HashMap<String, Boolean>(){{
			put("flag", false);
		}
	};
	public static ActiveMQConnectionFactory factory;
	
	public void init(String url){
		 factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
					ActiveMQConnectionFactory.DEFAULT_PASSWORD, url);
	}
	
	public void createTopic(String topicName) {
		Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Destination topic_destination = session.createTopic(topicName);
			MessageProducer messageProducer = session.createProducer(topic_destination);
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
			logger.info("create topic=" + topicName + " success");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void createDurableSubscriberForTopic(String topicName, String clientId, String durableSubscriberName) {
		Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.setClientID(clientId);
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Topic topic_destination = session.createTopic(topicName);
			MessageConsumer messageConsumer = session.createDurableSubscriber(topic_destination, durableSubscriberName);
			logger.info(messageConsumer);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendmessageToTopic(String topicName, String info) {
		Connection connection = null;
		try {
			connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Destination topic_destination = session.createTopic(topicName);
            MessageProducer messageProducer = session.createProducer(topic_destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(info);
            messageProducer.send(message);
            logger.info("send to " + topicName + " ok, info=" + info);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public Message getmessageFromTopictb(String topicName, String clientId, String durableSubscriberName, int waittime) {
		Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.setClientID(clientId);
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Topic topic_destination = session.createTopic(topicName);
			MessageConsumer messageConsumer = session.createDurableSubscriber(topic_destination, durableSubscriberName);
			//同步,等待10s
			Message message = messageConsumer.receive(waittime * 1000);
			if(message != null) {
				logger.info(message);
				message.acknowledge();
			}else {
				logger.warn("no message receive");
			}
			
//			CnTaskDoneMessage taskDoneMessage = null;
//			if(message != null)
//				taskDoneMessage = JSON.parseObject(((TextMessage) message).getText(), CnTaskDoneMessage.class);
//			System.out.println(taskDoneMessage);
			
			return message;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public void getmessageFromTopicyb(String topicName, String clientId, String durableSubscriberName, final int maxMessageCount) {
		long startTime = System.currentTimeMillis();
		Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.setClientID(clientId);
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Topic topic_destination = session.createTopic(topicName);
			final MessageConsumer messageConsumer = session.createDurableSubscriber(topic_destination, durableSubscriberName);
			// 异步接收
			messageConsumer.setMessageListener(new MessageListener() {
				int messageCount = 0;
				@Override
				public void onMessage(Message message) {
					if (message instanceof TextMessage) {
						try {
//							Thread.sleep(5000);
							message.acknowledge();
							messageCount++;
							System.out.println(messageCount + "--->" + message);
							if(messageCount >= maxMessageCount) {
								FL.put("flag", true);
								messageConsumer.close();
							}
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
			while(!FL.get("flag")) {
				long endTime = System.currentTimeMillis();
				if(endTime - startTime >= 1000 * 1000) {
					break;
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
//		System.out.println(JSON.toJSONString(new CnTaskDoneMessage("2018-07-13", "success")));
//		CnPoorCellTaskDoneMessage cnPoorCellTaskDoneMessage = new CnPoorCellTaskDoneMessage("2018-07-13", "success", "cn", "all");
//		System.out.println(JSON.toJSONString(cnPoorCellTaskDoneMessage));
		ActiveMqClient client = new ActiveMqClient();
		client.init("tcp://10.254.201.235:61616");
//		client.createTopic("com.nsn.messages.cmdi");
//		client.createDurableSubscriberForTopic("com.nsn.messages.cmdi", "volte", "cnpoorcgi");
//		client.createTopic("com.cmdi.volte.t4");
		client.createDurableSubscriberForTopic("com.cmdi.volte.t4", "volte", "hexinwangpoor");
//		Message message = client.getmessageFromTopictb("com.cmdi.volte.t1", "volte", "mroallcellday", 5);
//		System.out.println(message);
//		client.getmessageFromTopicyb("com.cmdi.volte.t1", "volte", "mroallcellday", 5);

		logger.info("task done");
	}
}