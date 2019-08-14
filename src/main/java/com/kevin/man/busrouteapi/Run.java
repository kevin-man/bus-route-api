package com.kevin.man.busrouteapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kevin.man.busrouteapi.dto.JsonRouteObject;
import com.kevin.man.busrouteapi.mapper.RouteMapper;
import com.kevin.man.busrouteapi.model.RouteModel;
import com.kevin.man.busrouteapi.model.StopModel;
import com.kevin.man.busrouteapi.repo.RouteRepository;
import com.kevin.man.busrouteapi.repo.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class Run {

    @Autowired
    public Run(RouteRepository routeRepository, StopRepository stopRepository) {
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    @Value("${json.file.path}")
    private String jsonPath;

    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final RouteMapper routeMapper = RouteMapper.INSTANCE;

    @PostConstruct
    private void init() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

        JsonRouteObject jsonRouteObject = objectMapper.readValue(new File(jsonPath), JsonRouteObject.class);

        List<RouteModel> routeModels = routeMapper.toRouteModels(jsonRouteObject.getRoutes());
        List<RouteModel> result = routeRepository.saveAll(routeModels);
        List<StopModel> stops = new ArrayList<>();
        for (RouteModel routeModel : result) {
            routeModel.getStops().forEach(stop -> stop.setRoute(routeModel));
            stops.addAll(routeModel.getStops());
        }

        stopRepository.saveAll(stops);
    }
}
