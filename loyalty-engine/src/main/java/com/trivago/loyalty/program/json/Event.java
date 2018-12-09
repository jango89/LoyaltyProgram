package com.trivago.loyalty.program.json;

import com.trivago.loyalty.program.domain.Reservation;

public class Event {

  private String clienId;
  private Double price;
  private String catalogId;
  private String hotelName;
  private String category;
  private Long pointsTotal;
  private String status;

  public Event(ItemResponse itemResponse, Reservation reservation, ClientResponse clientResponse) {
    this.clienId = clientResponse.getId();
    this.price = itemResponse.getPrice();
    this.catalogId = itemResponse.getId();
    this.hotelName = itemResponse.getHotelName();
    this.category = itemResponse.getCategory();
    this.pointsTotal = reservation.getPointsUsed();
    this.status = reservation.getReservationStatus().name();
  }

  public String getClienId() {
    return clienId;
  }

  public Double getPrice() {
    return price;
  }

  public String getCatalogId() {
    return catalogId;
  }

  public String getHotelName() {
    return hotelName;
  }

  public String getCategory() {
    return category;
  }

  public Long getPointsTotal() {
    return pointsTotal;
  }

  public String getStatus() {
    return status;
  }
}
