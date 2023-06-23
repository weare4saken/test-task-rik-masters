package com.wea4saken.rikmasters.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String passportData;
    private String licenseCategory;
    private Date birthDate;
    private Integer experience;
//    private List<Car> ownedCars;

//    public void addOwnedCar(Car car) {
//        ownedCars.add(car);
//        car.setOwner(this);
//    }
//
//    public void removeOwnedCar(Car car) {
//        ownedCars.remove(car);
//        car.setOwner(null);
//    }
//
//    public void replaceCar(Car oldCar, Car newCar) {
//        ownedCars.remove(oldCar);
//        ownedCars.add(newCar);
//        oldCar.setOwner(null);
//        newCar.setOwner(this);
//    }

}