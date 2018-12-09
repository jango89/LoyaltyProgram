package com.trivago.loyalty.program.json;

public class UpdateCatalogue {

  private String id;
  private Integer quantity;

  public UpdateCatalogue(String id, Integer quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public String getId() {
    return id;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
