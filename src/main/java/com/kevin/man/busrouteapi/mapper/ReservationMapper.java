package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.model.ReservationModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {StopMapper.class})
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationModel toReservationModel(final Reservation reservation);

    Reservation toReservation(final ReservationModel reservationModel);

}
