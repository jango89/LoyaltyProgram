package com.trivago.loyalty.program.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReserveWithPointsTest {

  @InjectMocks
  private ReserveWithPoints reserveWithPoints;

  @Mock
  private ClientInterface clientInterface;

  @Mock
  private CatalogueInterface catalogueInterface;

  @Mock
  private ReservationRepository reservationRepository;

  @Mock
  private EventPublisher eventPublisher;

  @Test
  public void givenClientAndItemAndQuantityShouldReserveWithAvailablePoints() {
    when(clientInterface.fetchClient("test")).thenReturn(clientResponse(1000L));
    when(catalogueInterface.fetchItem("itemId", 1)).thenReturn(itemResponse());
    doNothing().when(clientInterface).redeem(any(), any());
    doNothing().when(catalogueInterface).update(any());
    when(catalogueInterface.fetchItem("itemId", 1)).thenReturn(itemResponse());
    when(reservationRepository.save(any())).thenReturn(reservation());
    reserveWithPoints.execute("test", "itemId", 1);
    verify(catalogueInterface, times(1)).fetchItem("itemId", 1);
    verify(clientInterface, times(1)).fetchClient("test");
    verify(catalogueInterface, times(1)).update(any());
    verify(clientInterface, times(1)).redeem(any(), any());
    verify(reservationRepository, times(1)).save(any());
  }

  @Test
  public void givenClientAndItemAndQuantityCannotReserveWithAvailablePoints() {
    when(clientInterface.fetchClient("test")).thenReturn(clientResponse(10L));
    when(catalogueInterface.fetchItem("itemId", 1)).thenReturn(itemResponse());
    when(catalogueInterface.fetchItem("itemId", 1)).thenReturn(itemResponse());
    when(reservationRepository.save(any())).thenReturn(reservation());
    reserveWithPoints.execute("test", "itemId", 1);
    verify(catalogueInterface, times(1)).fetchItem("itemId", 1);
    verify(clientInterface, times(1)).fetchClient("test");
    verify(catalogueInterface, times(0)).update(any());
    verify(clientInterface, times(0)).redeem(any(), any());
    verify(reservationRepository, times(1)).save(any());
  }

  private Reservation reservation() {
    return new Reservation(ReservationStatus.PENDING_APPROVAL, "test", 10L);
  }

  private UpdateCatalogue updateCatalogue() {
    return new UpdateCatalogue("itemId", 1);
  }

  private UpdateClient updateClient() {
    return new UpdateClient(10L);
  }

  private ItemResponse itemResponse() {
    final ItemResponse itemResponse = new ItemResponse();
    itemResponse.setHotelName("test");
    itemResponse.setPrice(10.00);
    return itemResponse;
  }

  private ClientResponse clientResponse(Long points) {
    final ClientResponse clientResponse = new ClientResponse();
    clientResponse.setTotalPoints(points);
    return clientResponse;
  }
}