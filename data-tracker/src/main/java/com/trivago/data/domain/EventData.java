package com.trivago.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EventData {

  @Id
  private String id;
  private String applicationName;
  private String json;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }
}
