package com.wea4saken.rikmasters.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

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
//    private List<Part> parts;
    private Driver owner;

//    public void setOwner(Driver owner) {
//        this.owner = owner;
//    }
//
//    public void addPart(Part part) {
//        parts.add(part);
//    }
//
//    public void replacePart(Part oldPart, Part newPart) {
//        parts.remove(oldPart);
//        parts.add(newPart);
//    }

}