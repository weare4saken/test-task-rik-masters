package com.wea4saken.rikmasters.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {

    private String serialNumber;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

}
