package com.kevin.man.busrouteapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Reservation {

    private UUID reservationId;

    @ApiModelProperty(required = true, value = "The id of the stop to have a reservation made for.")
    @NotNull @NotBlank
    private UUID stopId;

    @ApiModelProperty(required = true, value = "The name of the child to reserve a spot for.")
    @NotNull @NotBlank
    private String childName;

    @ApiModelProperty(hidden = true)
    private LocalDate date;

    @ApiModelProperty(hidden = true)
    private String RouteName;

}
