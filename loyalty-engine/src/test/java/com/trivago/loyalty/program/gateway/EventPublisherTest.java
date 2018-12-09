package com.trivago.loyalty.program.gateway;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trivago.loyalty.program.domain.Reservation;
import com.trivago.loyalty.program.domain.ReservationStatus;
import com.trivago.loyalty.program.json.ClientResponse;
import com.trivago.loyalty.program.json.Event;
import com.trivago.loyalty.program.json.ItemResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(MockitoJUnitRunner.class)
public class EventPublisherTest {

  @InjectMocks
  private EventPublisher eventPublisher;

  @Mock
  private TopicExchange topicExchange;
  @Mock
  private RabbitTemplate rabbitTemplate;

  @Test
  public void givenDataForEventPublishShouldPublishEvent() {
    when(topicExchange.getName()).thenReturn("test");
    eventPublisher.publish(new ClientResponse(), new ItemResponse(),
        new Reservation(ReservationStatus.PENDING_APPROVAL, "1", 10L));
    verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), any(byte[].class));
  }

}