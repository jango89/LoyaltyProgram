package com.trivago.loyalty.program.gateway;

import com.google.gson.Gson;
import com.trivago.loyalty.program.domain.Reservation;
import com.trivago.loyalty.program.json.ClientResponse;
import com.trivago.loyalty.program.json.Event;
import com.trivago.loyalty.program.json.ItemResponse;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

  private final TopicExchange topicExchange;
  private final RabbitTemplate rabbitTemplate;
  private final String ROUTING_KEY = "loyalty-program";
  private final Gson gson = new Gson();

  public EventPublisher(TopicExchange topicExchange,
      RabbitTemplate rabbitTemplate) {
    this.topicExchange = topicExchange;
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publish(final ClientResponse clientResponse, final ItemResponse itemResponse,
      final Reservation reservation) {

    final Event event = new Event(itemResponse, reservation, clientResponse);
    rabbitTemplate
        .convertAndSend(topicExchange.getName(), ROUTING_KEY, gson.toJson(event).getBytes());
  }
}
