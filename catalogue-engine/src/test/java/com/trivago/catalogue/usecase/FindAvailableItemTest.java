package com.trivago.catalogue.usecase;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.repository.ItemRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindAvailableItemTest {

  @InjectMocks
  private FindAvailableItem findAvailableItems;

  @Mock
  private ItemRepository itemRepository;

  @Test
  public void givenItemIdWhenFindingItemShouldReturnOneResult() {
    when(itemRepository.findByIdAndQuantityAvailableGreaterThanEqual("1", 1))
        .thenReturn(Optional.of(item()));
    final Item item = findAvailableItems.execute("1", 1);
    assertNotNull(item);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenItemIdWhenFindingItemShouldReturnException() {
    final Item item = findAvailableItems.execute("1", 1);
  }

  private Item item() {
    final Item item = new Item();
    item.setTotalQuantity(1);
    item.setCategory("Single Room");
    return item;
  }

}
