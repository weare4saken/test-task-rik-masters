package com.wea4saken.rikmasters.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverDto {

    @JsonIgnore
    private Long id;

    @Pattern(regexp = "\\b\\w+\\s\\w+\\s\\w+\\b", message = "Full name must consist of 3 words")
    private String fullName;

    @Pattern(regexp = "\\d{2} \\d{2} \\d{6}", message = "Passport data must be in the format 00 00 000000")
    private String passportData;

    @Pattern(regexp = "\\d{2} \\d{2} \\d{6}", message = "License category must be in the format 00 00 000000")
    private String licenseCategory;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Birth date must be in the format dd-MM-yyyy")
    private Instant birthDate;

    @Positive(message = "Experience must be a positive number")
    private Integer experience;

}