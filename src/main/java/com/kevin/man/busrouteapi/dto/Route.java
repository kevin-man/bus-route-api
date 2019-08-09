package com.kevin.man.busrouteapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Route {

    private UUID routeId;

    @ApiModelProperty(required = true, value = "The name of the route.")
    @NotNull @NotBlank
    private String routeName;

    @ApiModelProperty(required = true, value = "A list of stops that the route has.")
    @Valid @NotNull @NotEmpty
    private List<Stop> stops;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
}
