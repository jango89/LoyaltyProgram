package com.trivago.catalogue.usecase;

import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FindAvailableItem {

  private final ItemRepository itemRepository;

  public FindAvailableItem(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Item execute(String id, Integer quantity) {
    return itemRepository.findByIdAndQuantityAvailableGreaterThanEqual(id, quantity)
        .orElseThrow(() -> new IllegalArgumentException("Item not available"));
  }
}
