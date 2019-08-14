package com.kevin.man.busrouteapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Reservation {

    @ApiModelProperty(hidden = true)
    private UUID reservationId;

    @ApiModelProperty(required = true, value = "The id of the stop to have a reservation made for.")
    @NotNull
    private UUID stopId;

    @ApiModelProperty(required = true, value = "The name of the child to reserve a spot for.")
    @NotNull
    private String childName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @ApiModelProperty(hidden = true)
    private LocalDate date;

    @ApiModelProperty(hidden = true)
    private String routeName;

}
