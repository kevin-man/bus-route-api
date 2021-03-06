package com.kevin.man.busrouteapi;

import com.kevin.man.busrouteapi.controller.BusRouteController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunTests {


    @Autowired
    private BusRouteController controller;


    @Test
    public void contextLoads() {

        assertThat(controller).isNotNull();
    }
}
