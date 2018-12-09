package com.trivago.auth.configuration;

import com.trivago.auth.domain.ApiPolicy;
import com.trivago.auth.domain.Client;
import com.trivago.auth.domain.PolicyName;
import com.trivago.auth.repository.ApiPolicyRepository;
import com.trivago.auth.repository.ClientRepository;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitStartup {

  private final ClientRepository clientRepository;
  private final ApiPolicyRepository apiPolicyRepository;

  public InitStartup(ClientRepository clientRepository,
      ApiPolicyRepository apiPolicyRepository) {
    this.clientRepository = clientRepository;
    this.apiPolicyRepository = apiPolicyRepository;
  }

  @PostConstruct
  public void init() throws IOException {
    clientRepository.deleteById("5c0bfb3082adcb8b872fe7b9");
    Client client = new Client();
    client.setId("5c0bfb3082adcb8b872fe7b9");
    client.setTotalPoints(1200L);
    client.setFirstName("Jane");
    client.setLastName("Doe");
    clientRepository.save(client);

    ApiPolicy apiPolicy = new ApiPolicy();
    apiPolicy.setPolicy(PolicyName.PRIVATE);
    apiPolicy.setApiKey("API_KEY_PRIVATE");

    apiPolicyRepository.save(apiPolicy);

    apiPolicy = new ApiPolicy();
    apiPolicy.setPolicy(PolicyName.PUBLIC);
    apiPolicy.setApiKey("API_KEY_PUBLIC");
    apiPolicyRepository.save(apiPolicy);
  }

}
