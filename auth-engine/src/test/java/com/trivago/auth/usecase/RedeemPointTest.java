package com.trivago.auth.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trivago.auth.domain.Client;
import com.trivago.auth.repository.ClientRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RedeemPointTest {

  @InjectMocks
  private RedeemPoint redeemPoint;

  @Mock
  private ClientRepository clientRepository;

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private TopicExchange topicExchange;

  @Test
  public void givenClientIdAndPointsWhenRedeemingPointsShouldDecreasePoints() {
    final Optional<Client> client = client();
    when(clientRepository.findById("test")).thenReturn(client);
    redeemPoint.execute("test", 10L);
    verify(clientRepository, times(1)).save(any());
    assertEquals(client.get().getTotalPoints().longValue(), 10L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenClientIdAndPointsWhenRedeemingPointsShouldThrowException() {
    redeemPoint.execute("test", 10L);
  }

  private Optional<Client> client() {
    Client client = new Client();
    client.setTotalPoints(20L);
    return Optional.of(client);
  }
}