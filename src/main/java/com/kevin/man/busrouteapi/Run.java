package com.kevin.man.busrouteapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kevin.man.busrouteapi.dto.Route;
import com.kevin.man.busrouteapi.mapper.RouteMapper;
import com.kevin.man.busrouteapi.model.RouteModel;
import com.kevin.man.busrouteapi.repo.RouteRepository;
import com.kevin.man.busrouteapi.repo.StopRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class Run {

    @Autowired
    public Run(RouteRepository routeRepository, StopRespository stopRespository) {
        this.routeRepository = routeRepository;
        this.stopRespository = stopRespository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    @Value("${json.file.path}")
    private String jsonPath;

    private final RouteRepository routeRepository;
    private final StopRespository stopRespository;
    private final RouteMapper routeMapper = RouteMapper.INSTANCE;

    @PostConstruct
    private void init() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

        Route route = objectMapper.readValue(new File(jsonPath), Route.class);
        RouteModel routeModel = routeMapper.toRouteModel(route);

        RouteModel savedRouteModel = routeRepository.save(routeModel);

        routeModel.getStops().forEach(stop -> stop.setRoute(savedRouteModel));
        stopRespository.saveAll(routeModel.getStops());
    }
}
