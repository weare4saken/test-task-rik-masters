package com.wea4saken.rikmasters.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {

    @Id
    private String serialNumber;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

}