package com.trivago.auth.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "api_policy")
@CompoundIndex(name = "apiKey_idx", def = "{'apiKey' : 1}")
public class ApiPolicy {

  @Id
  private String id;
  private PolicyName policy;
  private String apiKey;
  private String module;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PolicyName getPolicy() {
    return policy;
  }

  public void setPolicy(PolicyName policy) {
    this.policy = policy;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }
}
