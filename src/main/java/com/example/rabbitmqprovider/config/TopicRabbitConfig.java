package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

  @Bean(RabbitMqConfig.FirstTopicQueue)
  public Queue firstTopicQueue(){
    return new Queue(RabbitMqConfig.FirstTopicQueue);
  }

  @Bean(RabbitMqConfig.SecondTopicQueue)
  public Queue secondTopicQueue(){
    return new Queue(RabbitMqConfig.SecondTopicQueue);
  }

  @Bean(RabbitMqConfig.TopicExchange)
  TopicExchange topicExchange(){
    return new TopicExchange(RabbitMqConfig.TopicExchange);
  }

  @Bean
  Binding bindingFirstTopicExchange(@Qualifier(RabbitMqConfig.FirstTopicQueue)Queue queue,@Qualifier(RabbitMqConfig.TopicExchange)TopicExchange topicExchange){
    return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConfig.FirstTopicRoutingKey);
  }

  @Bean
  Binding bindingSecondTopicExchange(@Qualifier(RabbitMqConfig.SecondTopicQueue)Queue queue,@Qualifier(RabbitMqConfig.TopicExchange)TopicExchange topicExchange){
    return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConfig.AllTopicRoutingKey);
  }
}
