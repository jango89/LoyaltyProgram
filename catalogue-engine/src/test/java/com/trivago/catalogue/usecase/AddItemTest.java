package com.trivago.catalogue.usecase;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import com.trivago.catalogue.json.AddItemDto;
import com.trivago.catalogue.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(MockitoJUnitRunner.class)
public class AddItemTest {

  @InjectMocks
  private AddItem addItem;

  @Mock
  private ItemRepository itemRepository;

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private TopicExchange topicExchange;

  @Test
  public void givenItemDetailsShouldCreateItemInCatalogue() {
    addItem.execute(addItem());
    verify(itemRepository, times(1)).save(any());
  }

  private AddItemDto addItem() {
    AddItemDto addItemDto = new AddItemDto();
    addItemDto.setCategory("category");
    addItemDto.setQuantity(1);
    return addItemDto;
  }
}
