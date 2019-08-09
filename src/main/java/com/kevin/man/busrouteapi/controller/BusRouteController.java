package com.kevin.man.busrouteapi.controller;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "bus-routes", produces = "application/json")
public class BusRouteController implements BusRouteControllerApi {


    @PostMapping(value = "{issuerId}/validate-deal", consumes = {"application/json"})
    @ResponseStatus(NO_CONTENT)
    public List<Route> getRoutes() {
        return null;
    }

    @Override
    public Route getRoute(String routeName) {
        return null;
    }

    @Override
    public Route getReservations(String routeName, LocalDate date) {
        return null;
    }

    @Override
    public Reservation createReservation(String routeName, LocalDate date, Reservation reservation) {
        return null;
    }

    @Override
    public Reservation updateReservation(String routeName, LocalDate date, UUID reservationId, Reservation reservation) {
        return null;
    }

    @Override
    public ResponseEntity deleteReservation(String routeName, LocalDate date, UUID reservationId) {
        return null;
    }

    @Override
    public Reservation getReservation(String routeName, LocalDate date, UUID reservationId) {
        return null;
    }
}
