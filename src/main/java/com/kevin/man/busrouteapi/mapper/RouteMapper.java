package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.model.RouteModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    Route toRoute(RouteModel routeModel);

    RouteModel toRouteModel(Route route);
}