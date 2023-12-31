package com.wea4saken.rikmasters.repository;

import com.wea4saken.rikmasters.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

}