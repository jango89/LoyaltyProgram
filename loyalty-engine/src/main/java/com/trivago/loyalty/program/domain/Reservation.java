package com.trivago.loyalty.program.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reservation {

  @Id
  private String id;
  private ReservationStatus reservationStatus;
  private LocalDateTime executedOn;
  private String clientId;
  private Long pointsUsed;

  public Reservation(ReservationStatus reservationStatus, String clientId, Long pointsUsed) {
    this.reservationStatus = reservationStatus;
    this.clientId = clientId;
    this.pointsUsed = pointsUsed;
    this.executedOn = LocalDateTime.now();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ReservationStatus getReservationStatus() {
    return reservationStatus;
  }

  public void setReservationStatus(ReservationStatus reservationStatus) {
    this.reservationStatus = reservationStatus;
  }

  public LocalDateTime getExecutedOn() {
    return executedOn;
  }

  public void setExecutedOn(LocalDateTime executedOn) {
    this.executedOn = executedOn;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Long getPointsUsed() {
    return pointsUsed;
  }

  public void setPointsUsed(Long pointsUsed) {
    this.pointsUsed = pointsUsed;
  }
}
