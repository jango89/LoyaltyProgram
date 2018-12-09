package com.trivago.loyalty.program.usecase;

import static com.trivago.loyalty.program.domain.ReservationStatus.*;

import com.trivago.loyalty.program.domain.Points;
import com.trivago.loyalty.program.domain.Reservation;
import com.trivago.loyalty.program.domain.ReservationStatus;
import com.trivago.loyalty.program.gateway.CatalogueInterface;
import com.trivago.loyalty.program.gateway.ClientInterface;
import com.trivago.loyalty.program.gateway.EventPublisher;
import com.trivago.loyalty.program.json.ClientResponse;
import com.trivago.loyalty.program.json.ItemResponse;
import com.trivago.loyalty.program.json.UpdateCatalogue;
import com.trivago.loyalty.program.json.UpdateClient;
import com.trivago.loyalty.program.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReserveWithPoints {

  private final ClientInterface clientInterface;
  private final CatalogueInterface catalogueInterface;
  private final ReservationRepository reservationRepository;
  private final EventPublisher eventPublisher;

  public ReserveWithPoints(ClientInterface clientInterface,
      CatalogueInterface catalogueInterface,
      ReservationRepository reservationRepository,
      EventPublisher eventPublisher) {
    this.clientInterface = clientInterface;
    this.catalogueInterface = catalogueInterface;
    this.reservationRepository = reservationRepository;
    this.eventPublisher = eventPublisher;
  }

  public ReservationStatus execute(String clientId, String itemId, Integer quantity) {
    final ItemResponse itemResponse = catalogueInterface.fetchItem(itemId, quantity);
    final Long pointsNeeded = new Points(itemResponse.getPrice())
        .calculate();

    final ClientResponse clientResponse = clientInterface.fetchClient(clientId);

    Reservation reservation = reserve(quantity, itemResponse, pointsNeeded, clientResponse);
    eventPublisher.publish(clientResponse, itemResponse, reservation);
    return reservation.getReservationStatus();
  }

  private Reservation reserve(Integer quantity, ItemResponse itemResponse, Long pointsNeeded,
      ClientResponse clientResponse) {
    if (clientResponse.hasEnoughPoints(pointsNeeded)) {
      updateQuantity(itemResponse, quantity);
      updateClient(clientResponse, pointsNeeded);
      return insertToDatabase(clientResponse, pointsNeeded, RESERVED);
    } else {
      return insertToDatabase(clientResponse, pointsNeeded, PENDING_APPROVAL);
    }
  }

  private Reservation insertToDatabase(ClientResponse clientResponse, Long points,
      ReservationStatus status) {
    return reservationRepository.save(new Reservation(status, clientResponse.getId(), points));
  }

  private void updateClient(ClientResponse clientResponse, Long pointsNeeded) {
    clientInterface.redeem(clientResponse.getId(), new UpdateClient(pointsNeeded));
  }

  private void updateQuantity(ItemResponse itemResponse, Integer quantity) {
    catalogueInterface.update(new UpdateCatalogue(itemResponse.getId(), quantity));
  }
}
