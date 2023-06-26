package com.wea4saken.rikmasters.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {


    @Pattern(regexp = "^[A-Z0-9]{17}$")
    private String vin;

    @Pattern(regexp = "[A-Z]\\d{3}[A-Z]{2}")
    private String licensePlate;

    private String producer;

    private String model;

    private Integer productionYear;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<DetailDto> details;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DriverDto driver;

}