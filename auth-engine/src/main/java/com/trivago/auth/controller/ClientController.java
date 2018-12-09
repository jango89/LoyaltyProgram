package com.trivago.auth.controller;

import com.trivago.auth.domain.Client;
import com.trivago.auth.json.UpdateClientDTO;
import com.trivago.auth.usecase.FindClient;
import com.trivago.auth.usecase.GrantPoint;
import com.trivago.auth.usecase.RedeemPoint;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

  private final GrantPoint grantPoint;
  private final RedeemPoint redeemPoint;
  private final FindClient findClient;

  public ClientController(GrantPoint grantPoint, RedeemPoint redeemPoint,
      FindClient findClient) {
    this.grantPoint = grantPoint;
    this.redeemPoint = redeemPoint;
    this.findClient = findClient;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Client get(@PathVariable("id") String id) {
    return findClient.execute(id);
  }

  @PutMapping("/{id}/grant")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void grant(@PathVariable("id") String id, @Valid @RequestBody UpdateClientDTO updateClientDTO) {
    grantPoint.execute(id, updateClientDTO.getPoints());
  }

  @PutMapping("/{id}/redeem")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void redeem(@PathVariable("id") String id, @Valid @RequestBody UpdateClientDTO updateClientDTO) {
    redeemPoint.execute(id, updateClientDTO.getPoints());
  }
}
