package com.trivago.loyalty.program.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointsTest {

  @Test
  public void givenPriceWhenCalculatePointsShouldReturnCorrectPoints() {
    long points = new Points(12.00)
        .calculate();
    assertEquals(1200, points);
  }

  @Test
  public void givenPriceWithMoreDecimalValuesWhenCalculatePointsShouldReturnCorrectPoints() {
    long points = new Points(12.10101)
        .calculate();
    assertEquals(1210, points);
  }
}