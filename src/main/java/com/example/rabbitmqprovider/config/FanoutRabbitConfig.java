package com.example.rabbitmqprovider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

  @Bean(RabbitMqConfig.FanoutQueueA)
  Queue fanoutQueueA(){
    return new Queue(RabbitMqConfig.FanoutQueueA);
  }

  @Bean(RabbitMqConfig.FanoutQueueB)
  Queue fanoutQueueB(){
    return new Queue(RabbitMqConfig.FanoutQueueB);
  }

  @Bean(RabbitMqConfig.FanoutExchange)
  FanoutExchange fanoutExchange(){
    return new FanoutExchange(RabbitMqConfig.FanoutExchange);
  }

  @Bean
  Binding bindingFanoutExchangeA(@Qualifier(RabbitMqConfig.FanoutQueueA)Queue queue,@Qualifier(RabbitMqConfig.FanoutExchange)FanoutExchange fanoutExchange){
    return BindingBuilder.bind(queue).to(fanoutExchange);
  }

  @Bean
  Binding bindingFanoutExchangeB(@Qualifier(RabbitMqConfig.FanoutQueueB)Queue queue,@Qualifier(RabbitMqConfig.FanoutExchange)FanoutExchange fanoutExchange){
    return BindingBuilder.bind(queue).to(fanoutExchange);
  }
}
