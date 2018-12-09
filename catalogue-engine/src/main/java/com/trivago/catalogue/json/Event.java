package com.trivago.catalogue.json;

public class Event {

  private String itemId;
  private Integer oldQuantity;
  private Integer newQuantity;
  private Double itemPrice;
  private String operation;

  public Event(String itemId, Integer oldQuantity, Integer newQuantity, Double itemPrice,
      String operation) {
    this.itemId = itemId;
    this.oldQuantity = oldQuantity;
    this.newQuantity = newQuantity;
    this.itemPrice = itemPrice;
    this.operation = operation;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Integer getOldQuantity() {
    return oldQuantity;
  }

  public void setOldQuantity(Integer oldQuantity) {
    this.oldQuantity = oldQuantity;
  }

  public Integer getNewQuantity() {
    return newQuantity;
  }

  public void setNewQuantity(Integer newQuantity) {
    this.newQuantity = newQuantity;
  }

  public Double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(Double itemPrice) {
    this.itemPrice = itemPrice;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }
}
