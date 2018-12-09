package com.trivago.loyalty.program.json;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class ReservePointsRequest {

  @NotNull
  @NotEmpty
  private String clientId;

  @NotNull
  @NotEmpty
  private String itemId;

  @NotNull
  @Range(min = 1)
  private Integer quantity;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
