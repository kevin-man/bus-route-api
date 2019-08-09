package com.kevin.man.busrouteapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Stop {

    private UUID stopId;

    @ApiModelProperty(required = true, value = "The name of the stop")
    @NotNull @NotBlank
    private String stopName;

    @ApiModelProperty(required = true, value = "The time of pickup for the stop.")
    @NotNull @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;

    @ApiModelProperty(required = true, value = "The latitude of the geographical coordinate of the stop.")
    @NotNull @NotBlank
    private Double latitude;

    @ApiModelProperty(required = true, value = "The longitude of the geographical coordinate of the stop.")
    @NotNull @NotBlank
    private Double longitude;

    @ApiModelProperty(value = "A list of current reservations made for the stop.")
    @Valid
    private List<Reservation> reservations;

}
