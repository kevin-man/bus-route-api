package com.kevin.man.busrouteapi.repo;

import com.kevin.man.busrouteapi.model.RouteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<RouteModel, UUID> {
}
