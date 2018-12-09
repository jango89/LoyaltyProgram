package com.trivago.data;


import com.google.gson.Gson;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.trivago.data.repository")
@EnableRabbit
public class DataTrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DataTrackerApplication.class, args);
  }

  @Bean
  public Gson gson() {
    return new Gson();
  }

  @Bean
  public CachingConnectionFactory connectionFactory(final RabbitProperties rabbitProperties) {

    CachingConnectionFactory cachingConnectionFactory = getConnectionFactory(rabbitProperties);

    final RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate(cachingConnectionFactory));
    rabbitAdmin.initialize();
    authInitializations(rabbitAdmin);
    lpInitializations(rabbitAdmin);
    catalogInitializations(rabbitAdmin);

    return cachingConnectionFactory;
  }

  private void authInitializations(RabbitAdmin rabbitAdmin) {
    final Queue queue = new Queue("auth-engine");
    final TopicExchange topicExchange = new TopicExchange("auth-engine");
    rabbitAdmin.declareQueue(queue);
    rabbitAdmin.declareExchange(topicExchange);
    rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange)
        .with("auth"));
  }

  private void catalogInitializations(RabbitAdmin rabbitAdmin) {
    final Queue queue = new Queue("catalogue-engine");
    final TopicExchange topicExchange = new TopicExchange("catalogue-engine");
    rabbitAdmin.declareQueue(queue);
    rabbitAdmin.declareExchange(topicExchange);
    rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange)
        .with("catalogue"));
  }

  private void lpInitializations(RabbitAdmin rabbitAdmin) {
    final Queue queue = new Queue("loyalty-program");
    final TopicExchange topicExchange = new TopicExchange("loyalty-program");
    rabbitAdmin.declareQueue(queue);
    rabbitAdmin.declareExchange(topicExchange);
    rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange)
        .with("loyalty-program"));
  }

  @Bean
  public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
    return new RabbitTemplate(cachingConnectionFactory);
  }

  private CachingConnectionFactory getConnectionFactory(final RabbitProperties rabbitProperties) {
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
    cachingConnectionFactory.setAddresses(rabbitProperties.getAddresses());
    cachingConnectionFactory.setUsername(rabbitProperties.getUsername());
    cachingConnectionFactory.setPassword(rabbitProperties.getPassword());
    cachingConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
    return cachingConnectionFactory;
  }
}
