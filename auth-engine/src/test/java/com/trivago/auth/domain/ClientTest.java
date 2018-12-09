package com.trivago.auth.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ClientTest {

  @Test
  public void givenClientShouldSubtractPoints() {
    final Client client = client();
    client.subtract(10L);
    assertEquals(0L, client.getTotalPoints().longValue());
  }

  private Client client() {
    Client client = new Client();
    client.setTotalPoints(10L);
    return client;
  }

  @Test
  public void givenClientShouldPlusPoints() {
    final Client client = client();
    client.plus(10L);
    assertEquals(20L, client.getTotalPoints().longValue());
  }
}