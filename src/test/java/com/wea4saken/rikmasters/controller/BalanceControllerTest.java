package com.wea4saken.rikmasters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wea4saken.rikmasters.enums.Currency;
import com.wea4saken.rikmasters.model.Balance;
import com.wea4saken.rikmasters.model.Driver;
import com.wea4saken.rikmasters.repository.DriverRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Driver driver = new Driver();

    @BeforeEach
    void setUp() {
        driver.setFullName("Test");
        driver.setPassportData("32 02 495041");
        driver.setLicenseCategory("42 02 536987");
        driver.setBirthDate(LocalDate.of(1990, 5, 15));
        driver.setExperience(4);
        driver.setBalance(0.0);
        driverRepository.save(driver);
    }

    @AfterEach
    void cleanUp() {
        driverRepository.delete(driver);
    }

    @Test
    public void testDepositOnBalanceReturnsOk() throws Exception {
        Balance balance = new Balance(Currency.RED, 100.0);

        mockMvc.perform(post("/driver/{id}/deposit", driver.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(balance)))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithdrawFromBalanceReturnsOk() throws Exception {
        Balance balance = new Balance(Currency.GREEN, 20.0);

        mockMvc.perform(post("/driver/{id}/withdraw", driver.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(balance)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBalanceReturnsOkWithValidDriverIdAndCurrency() throws Exception {
        Currency currency = Currency.BLUE;

        mockMvc.perform(get("/driver/{id}/balance", driver.getId())
                        .param("currency", currency.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

}