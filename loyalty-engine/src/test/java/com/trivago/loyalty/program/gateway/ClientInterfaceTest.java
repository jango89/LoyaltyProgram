package com.trivago.loyalty.program.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trivago.loyalty.program.json.ClientResponse;
import com.trivago.loyalty.program.json.UpdateClient;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientInterfaceTest {

  @Autowired
  private ClientInterface clientInterface;

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockRestServiceServer;

  @Before
  public void setup() {
    clientInterface.setApiKey("test");
    clientInterface.setUrl("test/");
    mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void givenClientIdShouldRetrieveClientResponse() throws JsonProcessingException {
    mockRestServiceServer.expect(requestTo(StringContains.containsString("client")))
        .andRespond(withSuccess(new ObjectMapper().writeValueAsBytes(itemResponse()),
            MediaType.APPLICATION_JSON));
    final ClientResponse itemResponse = clientInterface.fetchClient("1");
    assertNotNull(itemResponse);
    assertEquals(itemResponse.getTotalPoints().longValue(), 10);
    mockRestServiceServer.verify();
  }

  private ClientResponse itemResponse() {
    final ClientResponse clientResponse = new ClientResponse();
    clientResponse.setTotalPoints(10L);
    return clientResponse;
  }

  @Test
  public void givenUpdateClientDataShouldUpdateClientService() {
    mockRestServiceServer.expect(requestTo(StringContains.containsString("client")))
        .andRespond(withSuccess(new byte[0], MediaType.APPLICATION_JSON));
    clientInterface.redeem("1", updateClient());
    mockRestServiceServer.verify();
  }

  private UpdateClient updateClient() {
    return new UpdateClient(10L);
  }
}