package com.trivago.auth.usecase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.trivago.auth.domain.ApiPolicy;
import com.trivago.auth.domain.PolicyName;
import com.trivago.auth.exception.Forbidden;
import com.trivago.auth.repository.ApiPolicyRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindByApiKeyTest {

  @InjectMocks
  private FindByApiKey findByApiKey;

  @Mock
  private ApiPolicyRepository apiPolicyRepository;

  @Test
  public void givenApiKeyWhenFindingPolicyShouldFindPrivatePolicy() {
    when(apiPolicyRepository.findByApiKey("test")).thenReturn(policy(PolicyName.PRIVATE));
    final PolicyName policyName = findByApiKey.execute("test");
    assertEquals(PolicyName.PRIVATE, policyName);
  }

  private Optional<ApiPolicy> policy(PolicyName policyName) {
    ApiPolicy apiPolicy = new ApiPolicy();
    apiPolicy.setPolicy(policyName);
    return Optional.of(apiPolicy);
  }

  @Test
  public void givenApiKeyWhenFindingPolicyShouldFindPublicPolicy() {
    when(apiPolicyRepository.findByApiKey("test")).thenReturn(policy(PolicyName.PUBLIC));
    final PolicyName policyName = findByApiKey.execute("test");
    assertEquals(PolicyName.PUBLIC, policyName);
  }

  @Test(expected = Forbidden.class)
  public void givenApiKeyWhenFindingPolicyShouldThrowError() {
    findByApiKey.execute("test");
  }
}