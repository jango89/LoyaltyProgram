package com.trivago.data.gateway;


import static org.mockito.ArgumentMatchers.any;

import com.google.gson.Gson;
import com.trivago.data.repository.EventDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoyaltyProgramEventConsumerTest {

  private LoyaltyProgramEventConsumer loyaltyProgramEventConsumer;

  @Mock
  private EventDataRepository eventDataRepository;

  @Test
  public void givenMessageShouldSaveIntoRepository() {
    loyaltyProgramEventConsumer = new LoyaltyProgramEventConsumer(eventDataRepository, new Gson());
    loyaltyProgramEventConsumer.receiveMessage(new byte[1]);
    Mockito.verify(eventDataRepository, Mockito.times(1)).save(any());
  }
}