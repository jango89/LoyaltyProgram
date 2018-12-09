package com.trivago.auth.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class Client {

  @Id
  private String id;
  private Long totalPoints;
  private String firstName;
  private String lastName;
  @Version
  private Long version;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(Long totalPoints) {
    this.totalPoints = totalPoints;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public void subtract(long points) {
    totalPoints = totalPoints - points;
  }

  public void plus(long points) {
    totalPoints = totalPoints + points;
  }
}
