package com.wea4saken.rikmasters.service.impl;

import com.wea4saken.rikmasters.enums.Currency;
import com.wea4saken.rikmasters.exception.IncorrectArgumentException;
import com.wea4saken.rikmasters.model.Balance;
import com.wea4saken.rikmasters.model.Driver;
import com.wea4saken.rikmasters.repository.DriverRepository;
import com.wea4saken.rikmasters.service.BalanceService;
import com.wea4saken.rikmasters.service.BirthdayService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AboutDriverService implements BalanceService, BirthdayService {

    private final DriverRepository driverRepository;
    private final DriverService driverService;

    public void deposit(Balance balance, Long idDriver) {
        Driver driver = driverService.findById(idDriver);
        if (balance.getCurrency() == Currency.RED) {
            driver.setBalance(driver.getBalance() + balance.getAmount());
        } else if (balance.getCurrency() == Currency.GREEN) {
            driver.setBalance(driver.getBalance() + balance.getAmount() * 2.5);
        } else if (balance.getCurrency() == Currency.BLUE) {
            driver.setBalance(driver.getBalance() + balance.getAmount() * 2.5 * 0.6);
        }
        driverRepository.save(driver);
        log.info("Средства успешно начислены на счет водителя с ID {}", idDriver);
    }

    public void withdraw(Balance balance, Long idDriver) {
        Driver driver = driverService.findById(idDriver);
        if (balance.getCurrency() == Currency.RED) {
            driver.setBalance(driver.getBalance() - balance.getAmount());
        } else if (balance.getCurrency() == Currency.GREEN) {
            driver.setBalance(driver.getBalance() - balance.getAmount() * 2.5);
        } else if (balance.getCurrency() == Currency.BLUE) {
            driver.setBalance(driver.getBalance() - balance.getAmount() * 2.5 * 0.6);
        }
        driverRepository.save(driver);
        log.info("Средства успешно сняты со счета водителя с ID {}", idDriver);
    }

    public Double getBalance(Currency currency, Long idDriver) {
        Driver driver = driverService.findById(idDriver);
        if (currency == Currency.RED) {
            return driver.getBalance();
        } else if (currency == Currency.GREEN) {
            return driver.getBalance() / 2.5;
        } else if (currency == Currency.BLUE) {
            return driver.getBalance() / (2.5 * 0.6);
        }
        throw new IncorrectArgumentException();
    }

    @PostConstruct
    @Scheduled(cron = "0 0 * * *")
    public void checkBirthday() {
        List<String> drivers = driverRepository.findAllBirthdays();
        if (!drivers.isEmpty()) {
            log.info("У данных водителей сегодня день рождения {}", drivers);
        }
    }

}