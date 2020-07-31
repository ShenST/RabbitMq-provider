package com.example.rabbitmqprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RabbitmqProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqProviderApplication.class, args);
  }

}
