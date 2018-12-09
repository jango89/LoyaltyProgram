package com.trivago.loyalty.program.gateway;

import com.trivago.loyalty.program.json.ItemResponse;
import com.trivago.loyalty.program.json.UpdateCatalogue;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Component
public class CatalogueInterface {

  private final RestTemplate restTemplate;

  @Value("${catalogue-engine.apiKey}")
  private String apiKey;

  @Value("${catalogue-engine.url}")
  private String url;

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public CatalogueInterface(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ItemResponse fetchItem(String itemId, Integer quantity) {
    final URI uri = new UriTemplate(url.concat("/catalogue/{id}/{quantity}"))
        .expand(itemId, quantity);
    final HttpEntity<Object> httpEntity = new HttpEntity<>(getHeaders());
    return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ItemResponse.class)
        .getBody();
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("AUTHORIZATION", apiKey);
    return headers;
  }

  public void update(UpdateCatalogue catalogue) {
    final URI uri = new UriTemplate(url.concat("/catalogue")).expand();
    final HttpEntity<Object> httpEntity = new HttpEntity<>(catalogue, getHeaders());
    restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, ItemResponse.class);
  }

}
