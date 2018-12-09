package com.trivago.catalogue.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ItemTest {

  @Test
  public void givenItemWithAvailableQuantityOneWhenUpdateQuantityAgainShouldUpdateToQuantityZero() {
    final Item item = item(1);
    item.updateQuantity(1);
    assertEquals(item.getQuantityAvailable().intValue(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenItemWithAvailableQuantityZeroShouldThrowErrorWhenUpdatingQuantity() {
    final Item item = item(0);
    item.updateQuantity(1);
  }

  private Item item(Integer quantity) {
    final Item item = new Item();
    item.setQuantityAvailable(quantity);
    item.setTotalQuantity(quantity);
    return item;
  }
}