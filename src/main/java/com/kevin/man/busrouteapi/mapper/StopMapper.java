package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Stop;
import com.kevin.man.busrouteapi.model.StopModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StopMapper {

    StopMapper INSTANCE = Mappers.getMapper(StopMapper.class);

    Stop toStop(StopModel stopModel);

    StopModel toStopModel(Stop stop);

}
