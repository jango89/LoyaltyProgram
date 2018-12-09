package com.trivago.loyalty.program.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.trivago.loyalty.program.domain.ReservationStatus;
import com.trivago.loyalty.program.json.ReservePointsRequest;
import com.trivago.loyalty.program.usecase.ReserveWithPoints;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

  private final ReserveWithPoints reserveWithPoints;

  public ReserveController(ReserveWithPoints reserveWithPoints) {
    this.reserveWithPoints = reserveWithPoints;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public ReservationStatus reserve(@RequestBody ReservePointsRequest reservePointsRequest) {
    return reserveWithPoints
        .execute(reservePointsRequest.getClientId(), reservePointsRequest.getItemId(),
            reservePointsRequest.getQuantity());
  }
}
