package com.kevin.man.busrouteapi.repo;

import com.kevin.man.busrouteapi.model.StopModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StopRespository extends JpaRepository<StopModel, UUID> {
}
