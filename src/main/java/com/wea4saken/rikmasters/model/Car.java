package com.wea4saken.rikmasters.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    private String vin;
    private String licensePlate;
    private String producer;
    private String model;
    private Integer productionYear;
    private List<Detail> details;
    private Driver owner;

}