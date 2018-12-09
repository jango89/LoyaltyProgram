package com.trivago.catalogue.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.repository.ItemRepository;
import com.trivago.catalogue.usecase.FindAvailableItems;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
public class FindAvailableItemsTest {

  @InjectMocks
  private FindAvailableItems findAvailableItems;

  @Mock
  private ItemRepository itemRepository;

  @Test
  public void givenPageAndLimitWhenFindingItemsShouldReturnAtleastOneResult() {
    when(itemRepository.findAllByQuantityAvailableGreaterThan(0, PageRequest.of(0, 1)))
        .thenReturn(items());
    final List<Item> items = findAvailableItems.execute(1, 1);
    assertEquals(items.size(), 1);
  }

  @Test
  public void givenPageAndLimitWhenFindingItemsShouldReturnZeroResult() {
    final List<Item> items = findAvailableItems.execute(1, 1);
    assertEquals(items.size(), 0);
  }

  private List<Item> items() {
    List<Item> items = new LinkedList<>();
    final Item item = new Item();
    item.setTotalQuantity(1);
    item.setCategory("Single Room");
    items.add(item);
    return items;
  }

}
