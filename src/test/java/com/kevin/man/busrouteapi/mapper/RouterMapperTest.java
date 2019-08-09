package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.dto.Stop;
import com.kevin.man.busrouteapi.model.RouteModel;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;

public class RouterMapperTest {

    private RouteMapper routeMapper = RouteMapper.INSTANCE;

    @Test
    public void toRouteTestSuccess() {
        Route route = Route.builder()
                .routeId(UUID.randomUUID())
                .date(LocalDate.of(2019, 02, 01))
                .routeName("TEST_ROUTE")
                .stops(Collections.singletonList(Stop.builder()
                        .stopId(UUID.randomUUID())
                        .latitude(12.1)
                        .longitude(0.12)
                        .time(LocalTime.of(12, 20))
                        .stopName("TEST")
                        .reservations(Collections.singletonList(Reservation.builder()
                                .date(LocalDate.of(2019, 2, 1))
                                .childName("SAM")
                                .build()))
                        .build()))
                .build();

        RouteModel routeModel = routeMapper.toRouteModel(route);
        System.out.println(routeModel);
    }
}
