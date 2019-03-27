package com.serendipity.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class ActiveMqTest {
  /**
   * 点对点形式发送消息
   * 
   * @throws Exception
   */
  @Test
  public void testQueqeProducer() throws Exception {
    // 1.创建一个连接工厂对象，需要指定服务的ip及端口
    ActiveMQConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
    // 2.使用工厂对象创建一个Connection对象
    Connection connection = connectionFactory.createConnection();
    // 3.开启连接，调用Connection对象的start方法
    connection.start();
    // 4.创建一个Session对象
    // 第一个参数:是否开启事务.如果true开启事务，第二个参数无意义。一般不开启事务false。
    // 第二个参数:应答模式。
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 5.使用Session对象创建一个Destination对象，两种形式queqe、topic
    Queue queqe = session.createQueue("test-queue");
    // 6.使用Session对象创建一个Priducer对象
    MessageProducer producer = session.createProducer(queqe);
    // 7.创建一个Message对象，可以使用TextMessage/
    /*
     * ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
     * textMessage.setText("hello Activemq");
     */
    TextMessage textMessage = session.createTextMessage("hello Activemq");
    // 8.发送消息
    producer.send(textMessage);
    // 9.关闭资源
    producer.close();
    session.close();
    connection.close();
  }

  // 接受消息
  @Test
  public void testQueueConsumer() throws Exception {
    // 创建一个ConnectionFactory对象连接MQ服务器
    ActiveMQConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
    // 创建一个连接对象
    Connection connection = connectionFactory.createConnection();
    // 开启连接
    connection.start();
    // 使用Connection对象创建一个Session对象
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 创建一个Destnation对象，queue对象
    Queue queue = session.createQueue("spring-queue");
    // 使用session对象创建一个消费者对象
    MessageConsumer consumer = session.createConsumer(queue);
    // 接受消息
    consumer.setMessageListener(new MessageListener() {

      @Override
      public void onMessage(Message message) {
        // TODO Auto-generated method stub
        // 打印结果
        TextMessage textMessage = (TextMessage) message;
        String text;
        try {
          text = textMessage.getText();
          System.out.println(text);
        } catch (JMSException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    // 等待接受消息
    System.in.read();
    // 关闭资源
    consumer.close();
    session.close();
    connection.close();
  }

  @Test
  public void testTopicProducer() throws Exception {
    // 1.创建一个连接工厂对象，需要指定服务的ip及端口
    ActiveMQConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
    // 2.使用工厂对象创建一个Connection对象
    Connection connection = connectionFactory.createConnection();
    // 3.开启连接，调用Connection对象的start方法
    connection.start();
    // 4.创建一个Session对象
    // 第一个参数:是否开启事务.如果true开启事务，第二个参数无意义。一般不开启事务false。
    // 第二个参数:应答模式。
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 5.使用Session对象创建一个Destination对象，两种形式queqe、topic
    Topic topic = session.createTopic("test-topic");
    // 6.使用Session对象创建一个Priducer对象
    MessageProducer producer = session.createProducer(topic);
    // 7.创建一个Message对象，可以使用TextMessage/
    /*
     * ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
     * textMessage.setText("hello Activemq");
     */
    TextMessage textMessage = session.createTextMessage("topic message");
    // 8.发送消息
    producer.send(textMessage);
    // 9.关闭资源
    producer.close();
    session.close();
    connection.close();
  }

  // 接受消息
  @Test
  public void testTopicConsumer() throws Exception {
    // 创建一个ConnectionFactory对象连接MQ服务器
    ActiveMQConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
    // 创建一个连接对象
    Connection connection = connectionFactory.createConnection();
    // 开启连接
    connection.start();
    // 使用Connection对象创建一个Session对象
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 创建一个Destnation对象，queue对象
    Topic topic = session.createTopic("test-topic");
    // 使用session对象创建一个消费者对象
    MessageConsumer consumer = session.createConsumer(topic);
    // 接受消息
    consumer.setMessageListener(new MessageListener() {

      @Override
      public void onMessage(Message message) {
        // TODO Auto-generated method stub
        // 打印结果
        TextMessage textMessage = (TextMessage) message;
        String text;
        try {
          text = textMessage.getText();
          System.out.println(text);
        } catch (JMSException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    System.out.println("topic消费者2");
    // 等待接受消息
    System.in.read();
    // 关闭资源
    consumer.close();
    session.close();
    connection.close();
  }
}
