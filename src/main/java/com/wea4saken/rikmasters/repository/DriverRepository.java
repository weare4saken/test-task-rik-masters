package com.wea4saken.rikmasters.repository;

import com.wea4saken.rikmasters.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value =
            "SELECT full_name FROM driver " +
            "WHERE EXTRACT(MONTH FROM birth_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "AND EXTRACT(DAY FROM birth_date) = EXTRACT(DAY FROM CURRENT_DATE)",
            nativeQuery = true)
    List<String> findAllBirthdays();
}