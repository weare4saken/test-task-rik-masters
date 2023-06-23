package com.wea4saken.rikmasters.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailDto {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String serialNumber;
    private String type;

}