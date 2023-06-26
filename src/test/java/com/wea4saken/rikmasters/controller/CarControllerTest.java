package com.wea4saken.rikmasters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wea4saken.rikmasters.dto.CarDto;
import com.wea4saken.rikmasters.model.Car;
import com.wea4saken.rikmasters.model.Detail;
import com.wea4saken.rikmasters.model.Driver;
import com.wea4saken.rikmasters.repository.CarRepository;
import com.wea4saken.rikmasters.repository.DetailRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DetailRepository detailRepository;

    private final Car car = new Car();
    private final CarDto carDto = new CarDto();
    private final Driver driver = new Driver();
    private final Detail detail = new Detail();

    @BeforeEach
    void setUp() {
        car.setVin("12345678912345678");
        car.setLicensePlate("A000AA");
        car.setProducer("Test");
        car.setModel("Test too");
        car.setProductionYear(1990);
        carRepository.save(car);

        driver.setFullName("Test");
        driver.setPassportData("32 02 495041");
        driver.setLicenseCategory("42 02 536987");
        driver.setBirthDate(LocalDate.of(1990, 5, 15));
        driver.setExperience(4);
        driverRepository.save(driver);

        detail.setSerialNumber("1a2s3d4f");
        detail.setType("Test");
        detailRepository.save(detail);
    }

    @AfterEach
    void cleanUp() {
        carRepository.delete(car);
        driverRepository.delete(driver);
        detailRepository.delete(detail);
    }

    @Test
    public void testAddCarReturnCorrectAddedCarFromDatabase() throws Exception {
        carDto.setVin("87654321987654321");
        carDto.setLicensePlate("B111BB");
        carDto.setProducer("Test 2");
        carDto.setModel("Test too 2");
        carDto.setProductionYear(2000);

        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vin").value(carDto.getVin()))
                .andExpect(jsonPath("$.licensePlate").value(carDto.getLicensePlate()))
                .andExpect(jsonPath("$.producer").value(carDto.getProducer()))
                .andExpect(jsonPath("$.model").value(carDto.getModel()))
                .andExpect(jsonPath("$.productionYear").value(carDto.getProductionYear()));
    }

    @Test
    public void testUpdateCarReturnsUpdatedCar() throws Exception {
        car.setLicensePlate("C333CC");
        carRepository.save(car);

        mockMvc.perform(patch("/car/{vin}", car.getVin())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate").value(car.getLicensePlate()));
    }

    @Test
    public void testGetAllCarsReturnsCorrectListOfCarsFromDatabase() throws Exception {
        mockMvc.perform(get("/car")
                        .param("pageNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAllCarsReturnsCorrectListOfCars() throws Exception {
        int pageNumber = 1;
        int pageSize = 1;

        mockMvc.perform(get("/car")
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(pageSize));
    }

    @Test
    public void testGetCarrReturnsCorrectCarrFromDatabase() throws Exception {
        mockMvc.perform(get("/car/{vin}", car.getVin()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin").value(car.getVin()))
                .andExpect(jsonPath("$.licensePlate").value(car.getLicensePlate()))
                .andExpect(jsonPath("$.producer").value(car.getProducer()))
                .andExpect(jsonPath("$.model").value(car.getModel()))
                .andExpect(jsonPath("$.productionYear").value(car.getProductionYear()));
    }

    @Test
    public void testDeleteCarReturnsOkWhenCarDeleted() throws Exception {
        mockMvc.perform(delete("/car/{vin}", car.getVin()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddDriverToCarReturnsOkWhenDriverAdded() throws Exception {
        mockMvc.perform(post("/car/{vin}/add-driver/{id}", car.getVin(), driver.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddDetailToCarReturnsOkWhenDetailAdded() throws Exception {
        mockMvc.perform(post("/car/{vin}/add-detail/{type}", car.getVin(), detail.getType()))
                .andExpect(status().isOk());
    }

}