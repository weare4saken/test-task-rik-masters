package com.wea4saken.rikmasters.model;

import com.wea4saken.rikmasters.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Balance {

    private Currency currency;
    private Double amount;

}