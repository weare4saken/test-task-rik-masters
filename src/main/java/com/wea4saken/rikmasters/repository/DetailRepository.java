package com.wea4saken.rikmasters.repository;

import com.wea4saken.rikmasters.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, String> {



}