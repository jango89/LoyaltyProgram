package com.trivago.loyalty.program.gateway;

import com.trivago.loyalty.program.json.ClientResponse;
import com.trivago.loyalty.program.json.UpdateClient;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Component
public class ClientInterface {

  private final RestTemplate restTemplate;

  @Value("${auth-engine.apiKey}")
  private String apiKey;

  @Value("${auth-engine.url}")
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public ClientInterface(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ClientResponse fetchClient(String clientId) {
    final URI uri = new UriTemplate(url.concat("/client/{id}"))
        .expand(clientId);
    final HttpEntity<Object> httpEntity = new HttpEntity<>(getHeaders());
    return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ClientResponse.class)
        .getBody();
  }

  public String retrievePolicy(String authorization) {
    final URI uri = new UriTemplate(url.concat("/authorize")).expand();
    HttpHeaders headers = new HttpHeaders();
    headers.add("AUTHORIZATION", authorization);
    final HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
    return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class)
        .getBody();
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("AUTHORIZATION", apiKey);
    return headers;
  }

  public void redeem(String clientId, UpdateClient updateClient) {
    final URI uri = new UriTemplate(url.concat("/client/{id}/redeem"))
        .expand(clientId);
    final HttpEntity<Object> httpEntity = new HttpEntity<>(updateClient, getHeaders());
    restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, ClientResponse.class);
  }
}
