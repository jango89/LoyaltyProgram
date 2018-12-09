package com.trivago.catalogue.configuration;

import com.trivago.catalogue.domain.Item;
import com.trivago.catalogue.repository.ItemRepository;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitStartup {

  private final ItemRepository itemRepository;

  public InitStartup(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @PostConstruct
  public void init() throws IOException {
    Item item = new Item();
    item.setQuantityAvailable(2);
    item.setTotalQuantity(2);
    item.setCategory("2 Bedroom Apartment");
    item.setHotelName("Hotel 1");
    item.setPrice(20.00D);
    itemRepository.save(item);

    item = new Item();
    item.setQuantityAvailable(2);
    item.setTotalQuantity(2);
    item.setCategory("3 Bedroom Apartment");
    item.setHotelName("Hotel 1");
    item.setPrice(30.00D);
    itemRepository.save(item);
  }

}
