package com.kevin.man.busrouteapi.controller;


import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BusRouteControllerApi {

    /**
     * Retrieve all routes endpoint for acquiring all persisted routes.
     *
     * @return a List of Route objects.
     */
    @ApiOperation(value = "Get All Routes", notes = "Find all routes")
    @ApiResponses(value = @ApiResponse(code = 200, message = "All Route data retrieved successfully"))
    List<Route> getRoutes();

    /**
     * Retrieve a specific route endpoint for acquiring a route.
     *
     * @param routeName the name of the route in String.
     * @return the resulting route found in a Route Object.
     */
    @ApiOperation(value = "Get route by name", notes = "Find a specific route by route name")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Route data retrieved successfully"))
    Route getRoute(@RequestBody final String routeName);

    /**
     * Retrieve all Reservations of a route endpoint.
     *
     * @param routeName the name of the route in String.
     * @param date the date for the route in LocalDate.
     * @return the resulting reservations within a nested object in Route Object.
     */
    @ApiOperation(value = "Get All Reservation", notes = "Find all reservations for all stops on a route")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Reservation data retrieved successfully"))
    Route getReservations(@PathVariable final String routeName,
                                @PathVariable final LocalDate date);

    /**
     * Create Reservation endpoint for persisting a Reservation object.
     *
     * @param routeName the name of the route in String.
     * @param date the date for the route in LocalDate.
     * @param reservation the reservation object to be persistent.
     * @return the resulting persisted reservation.
     */
    @ApiOperation(value = "Create Reservation", notes = "Create reservation for a stop")
    @ApiResponses(value = @ApiResponse(code = 201, message = "Reservation successfully created"))
    Reservation createReservation(@PathVariable final String routeName,
                                  @PathVariable final LocalDate date,
                                  @RequestBody final Reservation reservation);

    /**
     * Update Reservation endpoint for editing a persisted Reservation.
     *
     * @param routeName the name of the route in String.
     * @param date the date for the route LocalDate.
     * @param reservationId the id of the persisted reservation.
     * @param reservation the reservation object to be updated to a originally persisted reservation object.
     * @return the resulting updated persisted reservation object.
     */
    @ApiOperation(value = "Update Reservation", notes = "Update reservation for a stop")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Reservation successfully updated"))
    Reservation updateReservation(@PathVariable final String routeName,
                                  @PathVariable final LocalDate date,
                                  @PathVariable final UUID reservationId,
                                  @RequestBody final Reservation reservation);

    /**
     * Delete Reservation endpoint to remove a currently persisted Reservation object.
     *
     * @param routeName the name of the route in String.
     * @param date the date for the route in LocalDate.
     * @param reservationId the id of the persisted reservation.
     * @return a responseEntity stating a successful delete of the persisted Reservation object.
     */
    @ApiOperation(value = "Delete Reservation", notes = "Delete reservation")
    @ApiResponses(value = @ApiResponse(code = 204, message = "Reservation successfully deleted"))
    ResponseEntity deleteReservation(@PathVariable final String routeName,
                                     @PathVariable final LocalDate date,
                                     @PathVariable final UUID reservationId);

    /**
     * Retrieve Reservation endpoint to retrieve a persisted Reservation.
     *
     * @param routeName the name of the route in String.
     * @param date the date for the route in LocalDate.
     * @param reservationId the id of the persisted reservation.
     * @return a resulting Reservation found.
     */
    @ApiOperation(value = "Get Reservation", notes = "Get Reservation")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Reservation date retrieved successfully"))
    Reservation getReservation(@PathVariable final String routeName,
                               @PathVariable final LocalDate date,
                               @PathVariable final UUID reservationId);

}
