package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.model.RouteModel;
import org.junit.Test;

import java.util.UUID;

import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildRoute;
import static com.kevin.man.busrouteapi.BusRouteDataTestUtil.buildRouteModel;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class RouterMapperTest {

    private RouteMapper routeMapper = RouteMapper.INSTANCE;

    private static final UUID ROUTE_ID = UUID.randomUUID();
    private static final Route ROUTE = buildRoute(ROUTE_ID);
    private static final RouteModel ROUTE_MODEL = buildRouteModel(ROUTE_ID);

    @Test
    public void toRouteTestSuccess() {
        Route result = routeMapper.toRoute(ROUTE_MODEL);

        assertNotNull(result);
        assertEquals(ROUTE.getRouteId(), result.getRouteId());
        assertEquals(ROUTE.getDate(), result.getDate());
        assertEquals(ROUTE.getRouteName(), result.getRouteName());
    }

    @Test
    public void toRouteModelSuccess() {
        RouteModel result = routeMapper.toRouteModel(ROUTE);

        assertNotNull(result);
        assertEquals(ROUTE_MODEL.getRouteId(), result.getRouteId());
        assertEquals(ROUTE_MODEL.getDate(), result.getDate());
        assertEquals(ROUTE_MODEL.getRouteName(), result.getRouteName());
    }
}
