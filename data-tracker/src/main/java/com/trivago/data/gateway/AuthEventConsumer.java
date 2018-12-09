package com.trivago.data.gateway;

import com.google.gson.Gson;
import com.trivago.data.domain.EventData;
import com.trivago.data.repository.EventDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "auth-engine")
public class AuthEventConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthEventConsumer.class);
  private final EventDataRepository eventDataRepository;
  private final Gson gson;

  public AuthEventConsumer(EventDataRepository eventDataRepository, Gson gson) {
    this.eventDataRepository = eventDataRepository;
    this.gson = gson;
  }

  @RabbitHandler
  public void receiveMessage(byte[] message) {
    final EventData eventData = new EventData();
    eventData.setApplicationName("auth-engine");
    eventData.setJson(gson.toJson(message));
    eventDataRepository.save(eventData);
    LOGGER.info("Event from auth engine saved");
  }
}
