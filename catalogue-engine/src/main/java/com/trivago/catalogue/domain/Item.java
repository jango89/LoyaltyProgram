package com.trivago.catalogue.domain;

import com.trivago.catalogue.json.AddItemDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "catelogue_item")
public class Item {

  @Id
  private String id;
  private String category;
  private String hotelName;
  private Double price;
  private Integer quantityAvailable;
  private Integer totalQuantity;
  @Version
  private Long version;

  public Item(AddItemDto addItemDto) {
    this.category = addItemDto.getCategory();
    this.hotelName = addItemDto.getHotelName();
    this.price = addItemDto.getPrice();
    this.quantityAvailable = addItemDto.getQuantity();
    this.totalQuantity = addItemDto.getQuantity();
  }

  public Item() {
  }

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQuantityAvailable() {
    return quantityAvailable;
  }

  public void setQuantityAvailable(Integer quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }

  public Integer getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(Integer totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  @Version
  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Item updateQuantity(Integer toUpdate) {
    final int updatedQuantity = getQuantityAvailable() - toUpdate;
    if (isNotLessThanZero(updatedQuantity)) {
      setQuantityAvailable(updatedQuantity);
    } else {
      throw new IllegalArgumentException("Quantity not available/invalid");
    }
    return this;
  }

  private boolean isNotLessThanZero(int updatedQuantity) {
    return updatedQuantity > -1;
  }
}
