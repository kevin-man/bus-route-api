package com.kevin.man.busrouteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// A Object to deserialize json data to a java object.
public class JsonRouteObject {

    List<Route> routes;
}
