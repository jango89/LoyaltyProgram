package com.trivago.loyalty.program.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trivago.loyalty.program.json.ItemResponse;
import com.trivago.loyalty.program.json.UpdateCatalogue;
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
public class CatalogueInterfaceTest {

  @Autowired
  private CatalogueInterface catalogueInterface;

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockRestServiceServer;

  @Before
  public void setup() {
    catalogueInterface.setApiKey("test");
    catalogueInterface.setUrl("test/");
    mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void givenItemIdAndQuantityShouldRetrieveItemResponse() throws JsonProcessingException {
    mockRestServiceServer.expect(requestTo(StringContains.containsString("catalogue")))
        .andRespond(withSuccess(new ObjectMapper().writeValueAsBytes(itemResponse()),
            MediaType.APPLICATION_JSON));
    final ItemResponse itemResponse = catalogueInterface.fetchItem("1", 1);
    assertNotNull(itemResponse);
    assertEquals(itemResponse.getHotelName(), "test");
    mockRestServiceServer.verify();
  }

  @Test
  public void givenUpdateCatalogueDataShouldUpdateCatalogueService()
      throws JsonProcessingException {
    mockRestServiceServer.expect(requestTo(StringContains.containsString("catalogue")))
        .andRespond(withSuccess(new byte[0], MediaType.APPLICATION_JSON));
    catalogueInterface.update(updateCatalogue());
    mockRestServiceServer.verify();
  }

  private UpdateCatalogue updateCatalogue() {
    return new UpdateCatalogue("1", 1);
  }

  private ItemResponse itemResponse() {
    ItemResponse itemResponse = new ItemResponse();
    itemResponse.setHotelName("test");
    return itemResponse;
  }
}