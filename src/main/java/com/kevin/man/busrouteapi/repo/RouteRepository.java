package com.kevin.man.busrouteapi.repo;

import com.kevin.man.busrouteapi.model.RouteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<RouteModel, UUID> {

    Optional<RouteModel> findByRouteName(String routeName);

    Optional<RouteModel> findByRouteNameAndDate(String routeName, LocalDate date);

}