package com.trivago.loyalty.program.domain;

import static java.math.BigDecimal.ROUND_UP;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

public class Points {

  private final BigDecimal ONE_UNIT_IS_HUNDRED_POINTS = new BigDecimal(100);
  private BigDecimal price;

  public Points(Double price) {
    this.price = new BigDecimal(price).setScale(2, HALF_UP);
  }

  public Long calculate() {
    return price.multiply(ONE_UNIT_IS_HUNDRED_POINTS)
        .setScale(0, ROUND_UP)
        .longValue();
  }
}
