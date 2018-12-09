package com.trivago.catalogue.json;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class AddItemDto {

  @NotNull
  @NotEmpty
  private String category;
  @NotNull
  @NotEmpty
  private String hotelName;
  @NotNull
  @Range(min = 1)
  private Double price;
  @NotNull
  private Integer quantity;

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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
