package com.trivago.auth.json;

public class Event {

  private String clientId;
  private Long pointsAvailable;
  private Long newPoints;
  private String operation;

  public Event(String clientId, Long pointsAvailable, Long newPoints, String operation) {
    this.clientId = clientId;
    this.pointsAvailable = pointsAvailable;
    this.newPoints = newPoints;
    this.operation = operation;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Long getPointsAvailable() {
    return pointsAvailable;
  }

  public void setPointsAvailable(Long pointsAvailable) {
    this.pointsAvailable = pointsAvailable;
  }

  public Long getNewPoints() {
    return newPoints;
  }

  public void setNewPoints(Long newPoints) {
    this.newPoints = newPoints;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }
}
