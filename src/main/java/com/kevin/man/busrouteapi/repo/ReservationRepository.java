package com.kevin.man.busrouteapi.repo;

import com.kevin.man.busrouteapi.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationModel, UUID> {

    Optional<ReservationModel> findByRouteNameAndDateAndReservationId(String routeName, LocalDate date,
                                                                      UUID reservationId);
}
