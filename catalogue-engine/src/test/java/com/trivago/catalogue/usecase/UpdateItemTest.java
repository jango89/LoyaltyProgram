package com.trivago.catalogue.usecase;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trivago.catalogue.json.UpdateItemDto;
import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.repository.ItemRepository;
import com.trivago.catalogue.usecase.UpdateItem;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UpdateItemTest {

  @InjectMocks
  private UpdateItem updateItem;

  @Mock
  private ItemRepository itemRepository;

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private TopicExchange topicExchange;

  @Test
  public void givenItemDetailsShouldUpdateItemQuantityInCatalogue() {
    when(itemRepository.findById("test")).thenReturn(item(2));
    updateItem.execute(updateItem());
    verify(itemRepository, times(1)).save(any());
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenItemDetailsWhenItemHaveZeroAvailableQuantityShouldThrowIllegalException() {
    when(itemRepository.findById("test")).thenReturn(item(0));
    updateItem.execute(updateItem());
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenItemDetailsWhenItemIsNotFoundShouldThrowIllegalException() {
    when(itemRepository.findById("test")).thenReturn(Optional.empty());
    updateItem.execute(updateItem());
  }

  private Optional<Item> item(Integer quantity) {
    Item item = new Item();
    item.setQuantityAvailable(quantity);
    return Optional.of(item);
  }

  private UpdateItemDto updateItem() {
    UpdateItemDto updateItemDto = new UpdateItemDto();
    updateItemDto.setId("test");
    updateItemDto.setQuantity(1);
    return updateItemDto;
  }
}
