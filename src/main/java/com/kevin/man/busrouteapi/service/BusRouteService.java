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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BusRouteService {

    private RouteRepository routeRepository;
    private StopRepository stopRepository;
    private ReservationRepository reservationRepository;
    private RouteMapper routeMapper = RouteMapper.INSTANCE;

    @Autowired
    public BusRouteService(RouteRepository routeRepository, StopRepository stopRepository,
                           ReservationRepository reservationRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
        this.reservationRepository = reservationRepository;
        this.routeMapper = routeMapper;
    }

    /**
     * Finds all persisted routes.
     *
     * @return List of {@link Route} of all persisted routes.
     */
    public List<Route> getRoutes() {
        List<RouteModel> routeModels = routeRepository.findAll();

        return routeMapper.toRouteIgnoreStops(routeModels);
    }


    /**
     * Finds a particular persisted route.
     *
     * @param routeName the name of the persisted route to be found.
     * @return The found persisted route.
     */
    public Route getRoute(final String routeName) {
        RouteModel routeModel = routeRepository.findByRouteName(routeName).orElseThrow(() ->
                new NotFoundException("Route Not Found"));

        return routeMapper.toRouteIgnoreResignation(routeModel);
    }

    /**
     * Finds a particular persisted route and all stops mapped to the route.
     *
     * @param routeName the name of the persisted route to be found.
     * @param date the date of which the route is to made.
     * @return The found persisted route with a List of {@link com.kevin.man.busrouteapi.dto.Stop} that are mapped to
     *      the route.
     */
    public Route getRouteAndStops(final String routeName, final LocalDate date) {
        RouteModel routeModel = routeRepository.findByRouteNameAndDate(routeName, date)
                .orElseThrow(() -> new NotFoundException("Route Not Found"));

        return routeMapper.toRoute(routeModel);
    }


    /**
     * Persists a reservation to a stop.
     *
     * @param routeName the name of the route which the stop is to have a reservation persisted to.
     * @param date the date of the route.
     * @param reservation the {@link Reservation} object wih all reservation information to be persisted.
     * @return The persisted Reservation.
     */
    public Reservation createReservation(final String routeName, final LocalDate date, final Reservation reservation) {
        stopRepository.findById(reservation.getStopId()).orElseThrow(() ->
                new NotFoundException("Stop Not Found"));

        reservation.setRouteName(routeName);
        reservation.setDate(date);

        ReservationModel reservationModel = routeMapper.toReservationModel(reservation);

        ReservationModel savedReservation = reservationRepository.saveAndFlush(reservationModel);

        return routeMapper.toReservation(savedReservation);
    }


    /**
     * Updates a persisted reservation.
     *
     * @param routeName the name of the route.
     * @param date the date of the route to be used.
     * @param reservationId the id of the persisted route.
     * @param reservation the reservation to be updated to.
     * @return the updated reservation.
     */
    public Reservation updateReservation(final String routeName, final LocalDate date, final UUID reservationId,
                                         final Reservation reservation) {

        reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("Reservation Not Found"));
        reservation.setReservationId(reservationId);
        reservation.setRouteName(routeName);
        reservation.setDate(date);

        ReservationModel reservationModel = routeMapper.toReservationModel(reservation);

        ReservationModel savedReservation = reservationRepository.saveAndFlush(reservationModel);

        return routeMapper.toReservation(savedReservation);
    }

    /**
     * Deletes a persisted reservation.
     *
     * @param routeName the name of the route which the stop with the reservation is persisted to.
     * @param date the date of the route to be used.
     * @param reservationId the id of the persisted reservation.
     * @return a {@link ResponseEntity} with status code of 200 showing that the delete was successful.
     */
    public ResponseEntity deleteReservation(final String routeName, final LocalDate date, final UUID reservationId) {
        routeRepository.findByRouteNameAndDate(routeName, date).orElseThrow(() ->
                new NotFoundException("Route Not Found"));
        reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("Reservation Not Found"));

        reservationRepository.deleteById(reservationId);

        return ResponseEntity.status(200).body("Reservation: " + reservationId + " was successfully deleted");
    }

    /**
     * Finds a persisted reservation.
     *
     * @param routeName the name of the route which the stop with the reservation is persisted to.
     * @param date the date of the route to be used.
     * @param reservationId the id of the persisted reservation.
     * @return the found persisted reservation.
     */
    public Reservation getReservation(final String routeName, final LocalDate date, final UUID reservationId) {
        ReservationModel reservationModel = reservationRepository
                .findByRouteNameAndDateAndReservationId(routeName, date, reservationId).orElseThrow(() ->
                        new NotFoundException("Reservation Not Found"));

        return routeMapper.toReservation(reservationModel);
    }

}
