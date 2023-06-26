package com.wea4saken.rikmasters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wea4saken.rikmasters.dto.DriverDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final Driver driver = new Driver();
    private final DriverDto driverDto = new DriverDto();

    @BeforeEach
    void setUp() {
        driver.setFullName("Test");
        driver.setPassportData("32 02 495041");
        driver.setLicenseCategory("42 02 536987");
        driver.setBirthDate(LocalDate.of(1990, 5, 15));
        driver.setExperience(4);
        driverRepository.save(driver);
    }

    @AfterEach
    void cleanUp() {
        driverRepository.delete(driver);
    }

    @Test
    public void testAddDriverReturnCorrectAddedDriverFromDatabase() throws Exception {
        driverDto.setFullName("Test 2");
        driverDto.setPassportData("14 05 335841");
        driverDto.setLicenseCategory("77 04 111689");
        String date = "1985-12-10";
        driverDto.setBirthDate(LocalDate.parse(date));
        driverDto.setExperience(6);

        mockMvc.perform(post("/driver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driverDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value(driverDto.getFullName()))
                .andExpect(jsonPath("$.passportData").value(driverDto.getPassportData()))
                .andExpect(jsonPath("$.licenseCategory").value(driverDto.getLicenseCategory()))
                .andExpect(jsonPath("$.birthDate").value(date))
                .andExpect(jsonPath("$.experience").value(driverDto.getExperience()));
    }

    @Test
    public void testUpdateDriverReturnsUpdatedDriver() throws Exception {
        driver.setFullName("Test 3");
        driver.setPassportData("00 00 000000");
        driver.setLicenseCategory("11 11 111111");
        driver.setExperience(10);
        driverRepository.save(driver);

        mockMvc.perform(patch("/driver/{id}", driver.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(driver.getFullName()))
                .andExpect(jsonPath("$.passportData").value(driver.getPassportData()))
                .andExpect(jsonPath("$.licenseCategory").value(driver.getLicenseCategory()))
                .andExpect(jsonPath("$.experience").value(driver.getExperience()));
    }

    @Test
    public void testGetAllDriversReturnsCorrectListOfDriversFromDatabase() throws Exception {
        mockMvc.perform(get("/driver")
                .param("pageNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAllDriversReturnsCorrectListOfDrivers() throws Exception {
        int pageNumber = 1;
        int pageSize = 1;

        mockMvc.perform(get("/driver")
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(pageSize));
    }

    @Test
    public void testGetDriverReturnsCorrectDriverFromDatabase() throws Exception {
        mockMvc.perform(get("/driver/{id}", driver.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(driver.getFullName()))
                .andExpect(jsonPath("$.passportData").value(driver.getPassportData()))
                .andExpect(jsonPath("$.licenseCategory").value(driver.getLicenseCategory()))
                .andExpect(jsonPath("$.experience").value(driver.getExperience()));
    }

    @Test
    public void testDeleteDriverReturnsOkWhenDriverRemoved() throws Exception {
        mockMvc.perform(delete("/driver/{id}", driver.getId()))
                .andExpect(status().isOk());
    }

}