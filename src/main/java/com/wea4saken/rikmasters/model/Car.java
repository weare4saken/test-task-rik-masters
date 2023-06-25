package com.wea4saken.rikmasters.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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

    @JsonSetter
    public void setVin(String vin) {
        if (this.vin == null) this.vin = vin;
    }

    @JsonSetter
    public void setProducer(String producer) {
        if (this.producer == null) this.producer = producer;
    }

    @JsonSetter
    public void setModel(String model) {
        if (this.model == null) this.model = model;
    }

    @JsonSetter
    public void setProductionYear(Integer productionYear) {
        if (this.productionYear == null) this.productionYear = productionYear;
    }

}