package com.wea4saken.rikmasters.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wea4saken.rikmasters.model.Detail;
import com.wea4saken.rikmasters.model.Driver;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Size(min = 17, max = 17, message = "VIN number must be 17 characters long")
    private String vin;

    @Pattern(regexp = "[A-Z]\\d{3}[A-Z]{2}", message = "License plate must be in the format A000AA")
    private String licensePlate;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String producer;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String model;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Min(value = 1980, message = "Production year must be greater than 1980")
    private Integer productionYear;

    private List<Detail> details;

    private Driver owner;

}