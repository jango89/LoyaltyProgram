package com.trivago.catalogue.usecase;

import static org.springframework.data.domain.PageRequest.of;

import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.repository.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FindAvailableItems {

  private final ItemRepository itemRepository;

  public FindAvailableItems(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> execute(Integer page, Integer limit) {
    return itemRepository.findAllByQuantityAvailableGreaterThan(0,
        of(adjustPage(page), limit));
  }

  private int adjustPage(Integer page) {
    return page > 0 ? page - 1 : 0;
  }

}
