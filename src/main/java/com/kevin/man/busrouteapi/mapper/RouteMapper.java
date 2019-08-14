package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.dto.Stop;
import com.kevin.man.busrouteapi.model.ReservationModel;
import com.kevin.man.busrouteapi.model.RouteModel;
import com.kevin.man.busrouteapi.model.StopModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    // Mapping from RouteModel to Route
    Route toRoute(RouteModel routeModel);

    // Mapping from a List of RouteModel to List of Route
    // Uses "mapWithoutStop" to ignore Stop attribute field within Route
    @IterableMapping(qualifiedByName = "mapWithoutStop")
    List<Route> toRouteIgnoreStops(List<RouteModel> routeModels);

    // Mapping from a List of Route to List of RouteModel
    List<RouteModel> toRouteModels(List<Route> routes);

    // Mapping from Route to RouteModel
    RouteModel toRouteModel(Route route);

    // Mapping from StopModel to Stop
    Stop toStop(StopModel stopModel);

    // Mapping from Stop to StopModel
    StopModel toStopModel(Stop stop);

    // Mapping from a List of StopModel to List of Stop
    List<Stop> toStops(List<StopModel> stopModels);

    // Mapping from RouteModel to Route
    // Uses "mapWithoutReservation" to ignore Reservation field within Route
    @Mapping(target = "stops", qualifiedByName = "mapWithoutReservations")
    Route toRouteIgnoreResignation(RouteModel routeModel);

    // Mapping from Reservation to Reservation Model
    @Mapping(target = "stop.stopId", source = "stopId")
    ReservationModel toReservationModel(Reservation reservation);

    // Mapping from ReservationModel to Reservation
    @Mapping(target = "stopId",source = "stop.stopId")
    Reservation toReservation(ReservationModel reservationModel);

    // Mapping from RouteModel to Route
    // Ignores mapping Stops to Route
    @Named("mapWithoutStop")
    @Mapping(target = "stops", ignore = true)
    Route mapWithoutStop(RouteModel routeModel);

    // Mapping from StopModel to Stop
    @Named("mapWithoutReservations")
    @Mapping(target = "reservations", ignore = true)
    Stop mapWithoutResignation(StopModel stopModel);
}