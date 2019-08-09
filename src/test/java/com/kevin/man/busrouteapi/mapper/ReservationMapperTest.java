package com.kevin.man.busrouteapi.mapper;

import com.kevin.man.busrouteapi.dto.Reservation;
import com.kevin.man.busrouteapi.model.ReservationModel;
import org.junit.Test;

import java.time.LocalDate;

public class ReservationMapperTest {

    private ReservationMapper reservationMapper = ReservationMapper.INSTANCE;

    @Test
    public void toReservationModelSuccess() {
        Reservation reservation = Reservation.builder()
                .childName("TEST")
                .date(LocalDate.of(12, 01, 10))
                .build();

        ReservationModel reservationModel = reservationMapper.toReservationModel(reservation);

        System.out.println(reservationModel);
    }

}
