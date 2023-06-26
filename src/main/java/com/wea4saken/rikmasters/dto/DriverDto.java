package com.wea4saken.rikmasters.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String fullName;

    @Pattern(regexp = "\\d{2} \\d{2} \\d{6}")
    private String passportData;

    @Pattern(regexp = "\\d{2} \\d{2} \\d{6}")
    private String licenseCategory;

    private LocalDate birthDate;

    private Integer experience;

    private Double balance;

}