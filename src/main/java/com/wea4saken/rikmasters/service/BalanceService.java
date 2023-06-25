package com.wea4saken.rikmasters.service;

import com.wea4saken.rikmasters.enums.Currency;
import com.wea4saken.rikmasters.model.Balance;

public interface BalanceService {

    public void deposit(Balance balance, Long idDriver);
    public void withdraw(Balance balance, Long idDriver);
    public Double getBalance(Currency currency, Long idDriver);

}