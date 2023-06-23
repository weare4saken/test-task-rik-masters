package com.wea4saken.rikmasters.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Detail> details;
    @OneToOne
    private Driver driver;

}