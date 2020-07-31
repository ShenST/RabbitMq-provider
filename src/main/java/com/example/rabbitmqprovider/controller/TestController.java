package com.example.rabbitmqprovider.controller;

import com.example.rabbitmqprovider.config.RabbitMqConfig;
import com.example.rabbitmqprovider.entity.MessageLog;
import com.example.rabbitmqprovider.entity.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @PostMapping("/post")
  public String post(MessageLog messageLog){
    System.out.println("recieve from spring-boot(entity):"+messageLog.toString());
    return "post successfully(entity):"+messageLog.toString();
  }

  @GetMapping("/senddirectmessage")
  public String sendDirectMessage(){
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "hello: direct";
    String messageTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String,String> map = new HashMap<>();
    map.put("messageId",messageId);
    map.put("messageData",messageData);
    map.put("messageTime",messageTime);
    rabbitTemplate.convertAndSend(RabbitMqConfig.DirectExchange,RabbitMqConfig.DirectRoutingKey,map);
    System.out.println(map.toString());
    return "send direct message sucessfully!";
  }

  @GetMapping("/senddirectmessageuser")
  public String sendDirectMessageUser(){
    User user = new User(1L,"sst","123123123");
    rabbitTemplate.convertAndSend(RabbitMqConfig.DirectExchange,RabbitMqConfig.DirectRoutingKey,user);
    System.out.println(user.toString());
    return "send direct message sucessfully!";
  }

  @GetMapping("/sendfirsttopicmessage")
  public String sendFirstTopicMessage() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "hello: first topic";
    String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("messageId", messageId);
    map.put("messageData", messageData);
    map.put("messageTime", createTime);
    rabbitTemplate.convertAndSend(RabbitMqConfig.TopicExchange, RabbitMqConfig.FirstTopicRoutingKey, map);
    return "ok";
  }

  @GetMapping("/sendsecondtopicmessage")
  public String sendSecondTopicMessage() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "hello: second topic";
    String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("messageId", messageId);
    map.put("messageData", messageData);
    map.put("messageTime", createTime);
    rabbitTemplate.convertAndSend(RabbitMqConfig.TopicExchange, RabbitMqConfig.SencondTopicRoutingKey, map);
    return "ok";
  }

  @GetMapping("/sendfanoutmessage")
  public String sendFanoutMessage() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "hello: fanout";
    String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("messageId", messageId);
    map.put("messageData", messageData);
    map.put("messageTime", createTime);
    rabbitTemplate.convertAndSend(RabbitMqConfig.FanoutExchange,null, map);
    return "ok";
  }

  @GetMapping("/sendtest")
  public String sendTest() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "hello: test";
    String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("messageId", messageId);
    map.put("messageData", messageData);
    map.put("messageTime", createTime);
    rabbitTemplate.convertAndSend(RabbitMqConfig.DirectExchange,RabbitMqConfig.DirectRoutingKey, map);
    return "ok";
  }
}
