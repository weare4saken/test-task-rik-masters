package com.wea4saken.rikmasters.model;

import com.wea4saken.rikmasters.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Driver driver;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal amount;

}