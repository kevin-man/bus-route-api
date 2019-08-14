package com.kevin.man.busrouteapi.service;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.exception.NotFoundException;
import com.kevin.man.busrouteapi.mapper.RouteMapper;
import com.kevin.man.busrouteapi.model.ReservationModel;
import com.kevin.man.busrouteapi.model.RouteModel;
import com.kevin.man.busrouteapi.repo.ReservationRepository;
import com.kevin.man.busrouteapi.repo.RouteRepository;
import com.kevin.man.busrouteapi.repo.StopRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildReservation;
import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildReservationModel;
import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildRoute;
import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildRouteModel;
import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildStop;
import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildStopModel;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteServiceTest {

    @Mock
    private RouteRepository routeRepository;
    @Mock
    private StopRepository stopRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RouteMapper routeMapper;

    @InjectMocks
    private BusRouteService busRouteService;

    private static UUID ROUTE_ID = UUID.randomUUID();
    private static UUID STOP_ID = UUID.randomUUID();
    private static UUID RESERVATION_ID = UUID.randomUUID();
    private static LocalDate DATE = LocalDate.of(2019, 2, 2);


    @Test
    public void getRoutesSuccessful() {
        List<Route> routes = Collections.singletonList(new Route());

        when(routeRepository.findAll()).thenReturn(Collections.singletonList(new RouteModel()));
        when(routeMapper.toRouteIgnoreStops(any())).thenReturn(routes);
        List<Route> result = busRouteService.getRoutes();

        assertNotNull(result);
        assertEquals(routes, result);
    }

    @Test
    public void getRouteSuccessful() {
        RouteModel routeModel = buildRouteModel(ROUTE_ID);
        Route route = buildRoute(ROUTE_ID);

        when(routeRepository.findByRouteName(any())).thenReturn(Optional.of(routeModel));
        when(routeMapper.toRouteIgnoreResignation(any())).thenReturn(route);

        Route result = busRouteService.getRoute("TEST");

        assertNotNull(result);
        assertEquals(route, result);
    }

    @Test
    public void getRouteThrowsExceptionWhenNoRouteFound() {
        when(routeRepository.findByRouteName(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService.getRoute("TEST"))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Route Not Found");

    }

    @Test
    public void getRouteAndStopsSuccessful() {
        Route route = buildRoute(ROUTE_ID);
        route.setStops(Collections.singletonList(buildStop(STOP_ID, ROUTE_ID)));
        RouteModel routeModel = buildRouteModel(ROUTE_ID);
        routeModel.setStops(Collections.singletonList(buildStopModel(STOP_ID, ROUTE_ID)));


        when(routeRepository.findByRouteNameAndDate(any(), any())).thenReturn(Optional.of(routeModel));
        when(routeMapper.toRoute(any())).thenReturn(route);

        Route result = busRouteService.getRouteAndStops("TEST", DATE);

        assertNotNull(result);
        assertEquals(route, result);
    }

    @Test
    public void getRouteAndStopsThrowsExceptionWhenRouteNotFound() {
        when(routeRepository.findByRouteNameAndDate(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService.getRouteAndStops("TEST", DATE))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Route Not Found");
    }

    @Test
    public void createReservationSuccessful() {
        Reservation reservation = buildReservation(RESERVATION_ID, STOP_ID);

        when(reservationRepository.saveAndFlush(any())).thenReturn(buildReservationModel(RESERVATION_ID, STOP_ID));
        when(stopRepository.findById(any())).thenReturn(Optional.of(buildStopModel(STOP_ID, ROUTE_ID)));
        when(routeMapper.toReservation(any())).thenReturn(reservation);

        Reservation result = busRouteService.createReservation("TEST", DATE, reservation);

        assertNotNull(result);
        assertEquals(reservation, result);
    }

    @Test
    public void createReservationThrowsExceptionWhenStopNotFound() {
        when(stopRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService
                .createReservation("TEST", DATE, buildReservation(RESERVATION_ID, STOP_ID)))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Stop Not Found");
    }

    @Test
    public void updateReservationSuccessful() {
        Reservation reservation = buildReservation(RESERVATION_ID, ROUTE_ID);

        when(reservationRepository.findById(any())).thenReturn(Optional.of(new ReservationModel()));
        when(reservationRepository.saveAndFlush(any())).thenReturn(new ReservationModel());
        when(routeMapper.toReservation(any())).thenReturn(reservation);

        Reservation result = busRouteService.updateReservation("TEST", DATE, RESERVATION_ID, reservation);

        assertNotNull(result);
        assertEquals(reservation, result);
    }

    @Test
    public void updateReservationThrowsExceptionWhenReservationNotFound() {
        Reservation reservation = buildReservation(RESERVATION_ID, ROUTE_ID);

        when(reservationRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService.updateReservation("TEST", DATE, RESERVATION_ID, reservation))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Reservation Not Found");
    }

    @Test
    public void deleteReservationSuccessful() {
        when(routeRepository.findByRouteNameAndDate(any(), any())).thenReturn(Optional.of(new RouteModel()));
        when(reservationRepository.findById(any())).thenReturn(Optional.of(new ReservationModel()));

        ResponseEntity responseEntity = ResponseEntity.status(200).body("Reservation: " + RESERVATION_ID + " was successfully deleted");
        ResponseEntity result = busRouteService.deleteReservation("TEST", DATE, RESERVATION_ID);

        assertNotNull(result);
        assertEquals(responseEntity, result);
    }

    @Test
    public void deleteReservationSuccessfulThrowsExceptionWhenRouteNotFound() {
        when(routeRepository.findByRouteNameAndDate(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService.deleteReservation("TEST", DATE, RESERVATION_ID))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Route Not Found");
    }

    @Test
    public void deleteReservationSuccessfulThrowsExceptionWhenReservationNotFound() {
        when(routeRepository.findByRouteNameAndDate(any(), any())).thenReturn(Optional.of(new RouteModel()));
        when(reservationRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService.deleteReservation("TEST", DATE, RESERVATION_ID))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Reservation Not Found");
    }

    @Test
    public void getReservationSuccessful() {

        Reservation reservation = buildReservation(RESERVATION_ID, STOP_ID);

        when(reservationRepository.findByRouteNameAndDateAndReservationId(any(), any(), any())).thenReturn(
                Optional.of(buildReservationModel(RESERVATION_ID, STOP_ID)));
        when(routeMapper.toReservation(any())).thenReturn(reservation);

        Reservation result = busRouteService.getReservation("TEST", DATE, RESERVATION_ID);

        assertNotNull(result);
        assertEquals(reservation, result);
    }

    @Test
    public void getReservationThrowsExceptionWhenRouteNotFound() {
        when(reservationRepository.findByRouteNameAndDateAndReservationId(any(), any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> busRouteService.getReservation("TEST", DATE, RESERVATION_ID))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Reservation Not Found");
    }



}
