package com.trivago.auth.usecase;

import com.trivago.auth.domain.ApiPolicy;
import com.trivago.auth.domain.PolicyName;
import com.trivago.auth.exception.Forbidden;
import com.trivago.auth.repository.ApiPolicyRepository;
import org.springframework.stereotype.Service;

@Service
public class FindByApiKey {

  private final ApiPolicyRepository apiPolicyRepository;

  public FindByApiKey(ApiPolicyRepository apiPolicyRepository) {
    this.apiPolicyRepository = apiPolicyRepository;
  }

  public PolicyName execute(String apiKey) {
    return apiPolicyRepository.findByApiKey(apiKey)
        .map(ApiPolicy::getPolicy)
        .orElseThrow(Forbidden::new);
  }
}
