package com.trivago.auth.usecase;

import com.google.gson.Gson;
import com.trivago.auth.configuration.RabbitmqConfig;
import com.trivago.auth.domain.Client;
import com.trivago.auth.json.Event;
import com.trivago.auth.repository.ClientRepository;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrantPoint {

  private final ClientRepository clientRepository;
  private final RabbitTemplate rabbitTemplate;
  private final TopicExchange topicExchange;
  private final Gson gson = new Gson();

  public GrantPoint(ClientRepository clientRepository,
      RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
    this.clientRepository = clientRepository;
    this.rabbitTemplate = rabbitTemplate;
    this.topicExchange = topicExchange;
  }

  @Transactional
  @Retryable(value = OptimisticLockingFailureException.class, backoff = @Backoff(value = 2000))
  public void execute(String clientId, Long points) {
    clientRepository.findById(clientId)
        .map(item -> update(item, points))
        .orElseThrow(this::illegalArgumentException);
  }

  private Client update(Client client, Long points) {
    final Event event = new Event(client.getId(), client.getTotalPoints(), points, "GRANT");
    client.plus(points);
    clientRepository.save(client);
    rabbitTemplate.convertAndSend(topicExchange.getName(), RabbitmqConfig.ROUTING_KEY,
        gson.toJson(event).getBytes());
    return client;
  }

  private IllegalArgumentException illegalArgumentException() {
    throw new IllegalArgumentException("Client id not found");
  }
}
