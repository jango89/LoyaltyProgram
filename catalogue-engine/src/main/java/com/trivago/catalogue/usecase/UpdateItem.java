package com.trivago.catalogue.usecase;

import com.google.gson.Gson;
import com.trivago.catalogue.configuration.RabbitmqConfig;
import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.json.Event;
import com.trivago.catalogue.json.UpdateItemDto;
import com.trivago.catalogue.repository.ItemRepository;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateItem {

  private final ItemRepository itemRepository;
  private final RabbitTemplate rabbitTemplate;
  private final TopicExchange topicExchange;
  private final Gson gson = new Gson();

  public UpdateItem(ItemRepository itemRepository,
      RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
    this.itemRepository = itemRepository;
    this.rabbitTemplate = rabbitTemplate;
    this.topicExchange = topicExchange;
  }

  /**
   * OptimisticLockingFailureException thrown if @VERSION field inside MongoDocument(Item.java) is
   * not correct.
   */
  @Transactional
  @Retryable(value = OptimisticLockingFailureException.class, backoff = @Backoff(value = 2000))
  public void execute(UpdateItemDto updateItemDto) {
    itemRepository.findById(updateItemDto.getId())
        .map(item -> update(item, updateItemDto.getQuantity()))
        .orElseThrow(this::illegalArgumentException);
  }

  private Object update(Item item, Integer quantity) {
    Event event = new Event(item.getId(), item.getQuantityAvailable(),
        item.getQuantityAvailable() - quantity,
        item.getPrice(), "UPDATE");
    item.updateQuantity(quantity);
    itemRepository.save(item);
    rabbitTemplate.convertAndSend(topicExchange.getName(), RabbitmqConfig.ROUTING_KEY,
        gson.toJson(event).getBytes());
    return item;
  }

  private IllegalArgumentException illegalArgumentException() {
    throw new IllegalArgumentException("Item id not found");
  }

}
