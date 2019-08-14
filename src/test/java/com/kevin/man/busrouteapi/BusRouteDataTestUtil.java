package com.kevin.man.busrouteapi;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.dto.Stop;
import com.kevin.man.busrouteapi.model.ReservationModel;
import com.kevin.man.busrouteapi.model.RouteModel;
import com.kevin.man.busrouteapi.model.StopModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class BusRouteDataTestUtil {

    public static Reservation buildReservation(final UUID reservationId, final UUID stopId) {
        return Reservation.builder()
                .reservationId(reservationId)
                .childName("TEST")
                .date(LocalDate.of(2019, 10, 5))
                .routeName("TEST")
                .stopId(stopId)
                .build();
    }

    public static ReservationModel buildReservationModel(final UUID reservationId, final UUID stopId) {
        return ReservationModel.builder()
                .reservationId(reservationId)
                .childName("TEST")
                .date(LocalDate.of(2019, 10, 5))
                .routeName("TEST")
                .stop(StopModel.builder().stopId(stopId).build())
                .build();
    }

    public static Stop buildStop(final UUID stopId, final UUID routeId) {
        return Stop.builder()
                .stopId(stopId)
                .latitude(10.0)
                .longitude(10.0)
                .time(LocalTime.of(10,20))
                .stopName("TEST")
                .build();
    }

    public static StopModel buildStopModel(final UUID stopId, final UUID routeId) {
        return StopModel.builder()
                .stopId(stopId)
                .route(RouteModel.builder().routeId(routeId).build())
                .stopName("TEST")
                .latitude(10.0)
                .longitude(10.0)
                .time(LocalTime.of(10, 20))
                .route(RouteModel.builder().routeId(routeId).build())
                .build();
    }

    public static Route buildRoute(final UUID routeId) {
        return Route.builder()
                .routeId(routeId)
                .routeName("TEST")
                .date(LocalDate.of(2019, 10, 2))
                .build();
    }

    public static RouteModel buildRouteModel(final UUID routeId) {
        return RouteModel.builder()
                .routeId(routeId)
                .routeName("TEST")
                .date(LocalDate.of(2019, 10, 2))
                .build();
    }

}
