package com.trivago.auth.configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

  public static final String ROUTING_KEY = "auth";

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("auth-engine");
  }
}
