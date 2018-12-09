package com.trivago.auth.json;

import javax.validation.constraints.NotNull;

public class UpdateClientDTO {

  @NotNull
  private Long points;

  public Long getPoints() {
    return Math.abs(points);
  }

  public void setPoints(Long points) {
    this.points = points;
  }

}
