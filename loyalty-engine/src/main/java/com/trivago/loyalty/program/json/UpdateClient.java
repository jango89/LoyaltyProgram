package com.trivago.loyalty.program.json;

import javax.validation.constraints.NotNull;

public class UpdateClient {

  private Long points;

  public UpdateClient(@NotNull Long points) {
    this.points = points;
  }

  public Long getPoints() {
    return Math.abs(points);
  }
  
}
