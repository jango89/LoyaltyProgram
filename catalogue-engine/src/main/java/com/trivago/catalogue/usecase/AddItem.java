package com.trivago.catalogue.usecase;

import com.google.gson.Gson;
import com.trivago.catalogue.configuration.RabbitmqConfig;
import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.json.AddItemDto;
import com.trivago.catalogue.json.Event;
import com.trivago.catalogue.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddItem {

  private final ItemRepository itemRepository;
  private final Logger logger = LoggerFactory.getLogger(AddItem.class);
  private final RabbitTemplate rabbitTemplate;
  private final TopicExchange topicExchange;
  private final Gson gson = new Gson();

  public AddItem(ItemRepository itemRepository,
      RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
    this.itemRepository = itemRepository;
    this.rabbitTemplate = rabbitTemplate;
    this.topicExchange = topicExchange;
  }

  @Transactional
  public void execute(AddItemDto addItemDto) {
    Item item = new Item(addItemDto);
    itemRepository.save(item);
    logger.info("Item {} saved ", addItemDto.getCategory());
    Event event = new Event(item.getId(), item.getTotalQuantity(), item.getTotalQuantity(),
        item.getPrice(), "ADD");
    rabbitTemplate.convertAndSend(topicExchange.getName(), RabbitmqConfig.ROUTING_KEY,
        gson.toJson(event).getBytes());
  }
}
