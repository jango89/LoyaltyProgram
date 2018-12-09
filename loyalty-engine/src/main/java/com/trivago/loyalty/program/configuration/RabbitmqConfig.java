package com.trivago.loyalty.program.configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("loyalty-program");
  }
}
