package com.kevin.man.busrouteapi.controller;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping(produces = "application/json")
public class BusRouteController implements BusRouteControllerApi {

    private BusRouteService busRouteService;

    @Autowired
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    /**
     * {@inheritDoc}.
     */
    @GetMapping(value = "/routes")
    public List<Route> getRoutes() {
        return busRouteService.getRoutes();
    }

    /**
     * {@inheritDoc}.
     */
    @GetMapping(value = "/routes/{route_name}")
    public Route getRoute(@PathVariable("route_name") String routeName) {
        return busRouteService.getRoute(routeName);
    }

    /**
     * {@inheritDoc}.
     */
    @GetMapping(value = "/reservations/{route_name}/{date}")
    public Route getReservations(
            @PathVariable("route_name") final String routeName,
            @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate date) {

        return busRouteService.getRouteAndStops(routeName, date);
    }

    /**
     * {@inheritDoc}.
     */
    @PostMapping(value = "/reservations/{route_name}/{date}")
    public Reservation createReservation(
            @PathVariable("route_name") String routeName,
            @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate date,
            @RequestBody @Valid final Reservation reservation) {

        return busRouteService.createReservation(routeName, date, reservation);
    }

    /**
     * {@inheritDoc}.
     */
    @PutMapping(value = "/reservations/{route_name}/{date}/{reservations_id}")
    public Reservation updateReservation(
            @PathVariable("route_name") final String routeName,
            @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate date,
            @PathVariable("reservations_id") UUID reservationId,
            @RequestBody @Valid final Reservation reservation) {

        return busRouteService.updateReservation(routeName, date, reservationId, reservation);
    }

    /**
     * {@inheritDoc}.
     */
    @DeleteMapping("/reservations/{route_name}/{date}/{reservations_id}")
    public ResponseEntity deleteReservation(
            @PathVariable("route_name") final String routeName,
            @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate date,
            @PathVariable("reservations_id") final UUID reservationId) {

        return busRouteService.deleteReservation(routeName, date, reservationId);
    }

    /**
     * {@inheritDoc}.
     */
    @GetMapping("/reservations/{route_name}/{date}/{reservations_id}")
    public Reservation getReservation(
            @PathVariable("route_name") final String routeName,
            @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") final LocalDate date,
            @PathVariable("reservations_id") final UUID reservationId) {

        return busRouteService.getReservation(routeName, date,reservationId);
    }
}
