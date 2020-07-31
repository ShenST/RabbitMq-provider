package com.example.rabbitmqprovider.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String DirectQueue = "DirectQueue";
  public static final String DirectExchange = "DirectExchange";
  public static final String DirectRoutingKey = "DirectRouting";

  public static final String FirstTopicQueue = "FirstTopicQueue";
  public static final String SecondTopicQueue = "SecondTopicQueue";
  public static final String TopicExchange = "TopicExchange";
  public static final String FirstTopicRoutingKey = "TopicRoutingKey.first";
  public static final String SencondTopicRoutingKey = "TopicRoutingKey.second";
  public static final String AllTopicRoutingKey = "TopicRoutingKey.#";

  public static final String FanoutQueueA = "FanoutQueueA";
  public static final String FanoutQueueB = "FanoutQueueB";
  public static final String FanoutExchange = "FanoutExchange";

  @Bean
  public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory);
    rabbitTemplate.setMandatory(true);//设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数

    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
      System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
      System.out.println("ConfirmCallback:     "+"确认情况："+ack);
      System.out.println("ConfirmCallback:     "+"原因："+cause);
    });

    rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
      System.out.println("ReturnCallback:     "+"消息："+message);
      System.out.println("ReturnCallback:     "+"回应码："+replyCode);
      System.out.println("ReturnCallback:     "+"回应信息："+replyText);
      System.out.println("ReturnCallback:     "+"交换机："+exchange);
      System.out.println("ReturnCallback:     "+"路由键："+routingKey);
    });

    return rabbitTemplate;
  }
}
