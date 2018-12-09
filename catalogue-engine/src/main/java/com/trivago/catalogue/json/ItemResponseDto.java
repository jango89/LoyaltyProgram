package com.trivago.catalogue.json;

import com.trivago.catalogue.domain.Item;
import java.util.List;
import java.util.stream.Collectors;

public class ItemResponseDto {

  private List<ItemResponse> items;

  public ItemResponseDto(List<Item> items) {
    this.items = items.stream()
        .map(ItemResponse::new)
        .collect(Collectors.toList());
  }

  public List<ItemResponse> getItems() {
    return items;
  }

  class ItemResponse {

    private String id;
    private String category;
    private String hotelName;
    private Double price;
    private Integer quantityAvailable;

    ItemResponse(Item item) {
      this.id = item.getId();
      this.category = item.getCategory();
      this.hotelName = item.getHotelName();
      this.price = item.getPrice();
      this.quantityAvailable = item.getQuantityAvailable();
    }

    public String getId() {
      return id;
    }

    public String getCategory() {
      return category;
    }

    public String getHotelName() {
      return hotelName;
    }

    public Double getPrice() {
      return price;
    }

    public Integer getQuantityAvailable() {
      return quantityAvailable;
    }
  }
}
