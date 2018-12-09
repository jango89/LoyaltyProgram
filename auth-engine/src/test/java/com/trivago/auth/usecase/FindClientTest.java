package com.trivago.auth.usecase;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.trivago.auth.domain.Client;
import com.trivago.auth.repository.ClientRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindClientTest {

  @InjectMocks
  private FindClient findClient;

  @Mock
  private ClientRepository clientRepository;

  @Test
  public void givenClientIdWhenFindingClientsShouldReturnOneResult() {
    when(clientRepository.findById("1")).thenReturn(Optional.of(item()));
    final Client item = findClient.execute("1");
    assertNotNull(item);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenClientIdWhenFindingClientsShouldReturnException() {
    final Client item = findClient.execute("1");
  }

  private Client item() {
    final Client item = new Client();
    item.setFirstName("test");
    return item;
  }

}