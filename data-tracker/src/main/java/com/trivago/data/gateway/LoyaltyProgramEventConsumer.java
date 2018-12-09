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
@RabbitListener(queues = "loyalty-program")
public class LoyaltyProgramEventConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoyaltyProgramEventConsumer.class);
  private final EventDataRepository eventDataRepository;
  private final Gson gson;

  public LoyaltyProgramEventConsumer(EventDataRepository eventDataRepository, Gson gson) {
    this.eventDataRepository = eventDataRepository;
    this.gson = gson;
  }

  @RabbitHandler
  public void receiveMessage(byte[] message) {
    final EventData eventData = new EventData();
    eventData.setApplicationName("loyalty-program");
    eventData.setJson(gson.toJson(message));
    eventDataRepository.save(eventData);
    LOGGER.info("Event from Loyalty Program is saved");
  }
}
