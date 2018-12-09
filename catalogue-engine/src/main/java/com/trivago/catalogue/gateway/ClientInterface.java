package com.trivago.catalogue.gateway;

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

  public ClientInterface(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String retrievePolicy(String authorization) {
    final URI uri = new UriTemplate(url.concat("/authorize")).expand();
    HttpHeaders headers = new HttpHeaders();
    headers.add("AUTHORIZATION", authorization);
    final HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
    return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class)
        .getBody();
  }

}
