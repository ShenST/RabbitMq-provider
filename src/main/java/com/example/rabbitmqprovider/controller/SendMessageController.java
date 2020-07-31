package com.example.rabbitmqprovider.controller;

import com.example.rabbitmqprovider.config.RabbitMqConfig;
import com.example.rabbitmqprovider.entity.MessageLog;
import com.alibaba.fastjson.JSONObject;
import java.util.Random;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

  @Autowired
  RabbitTemplate rabbitTemplate;

  public static String getRandomString(int length){
    String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random=new Random();
    StringBuffer sb=new StringBuffer();
    for(int i=0;i<length;i++){
      int number=random.nextInt(62);
      sb.append(str.charAt(number));
    }
    return sb.toString();
  }

  @GetMapping("/send/{num}")
  public String sendDirectMessage(@PathVariable int num) {
    MessageLog messageLog = new MessageLog();
    for (int i = 0; i < num; i++){
      messageLog.setMessageId(String.valueOf(UUID.randomUUID()));
      messageLog.setExternalTransmission(getRandomString(8));
      messageLog.setInternalTransmission(getRandomString(8));
      messageLog.setMessageContent(getRandomString(8));
      messageLog.setProcessStage(getRandomString(8));
      messageLog.setProcessStageStatus(getRandomString(8));
      messageLog.setSelfBillingDocument(getRandomString(8));
      messageLog.setTenantID(getRandomString(8));
      System.out.println("messageLog(entity):"+messageLog);
      String strJson = JSONObject.toJSONString(messageLog);
      rabbitTemplate.convertAndSend(RabbitMqConfig.DirectExchange,RabbitMqConfig.DirectRoutingKey,strJson);
      System.out.println("send direct message(json):"+strJson);
    }
    return "send direct message sucessfully!";
  }
}
