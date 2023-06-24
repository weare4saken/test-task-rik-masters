package com.wea4saken.rikmasters.service.impl;

import com.wea4saken.rikmasters.dto.CarDto;
import com.wea4saken.rikmasters.exception.IncorrectArgumentException;
import com.wea4saken.rikmasters.exception.ItemNotFoundException;
import com.wea4saken.rikmasters.mapper.CarMapper;
import com.wea4saken.rikmasters.model.Car;
import com.wea4saken.rikmasters.model.Driver;
import com.wea4saken.rikmasters.repository.CarRepository;
import com.wea4saken.rikmasters.service.CRUDService;
import com.wea4saken.rikmasters.service.FindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CarService implements CRUDService<CarDto, String>, FindService<Car, String> {

    private final CarRepository carRepository;
    private final DriverService driverService;

    @Override
    public CarDto add(CarDto carDto) {
        log.debug("Adding car");
        if(!validCheck(carDto)) throw new IncorrectArgumentException();
        Car car = CarMapper.INSTANCE.toEntity(carDto);
        log.debug("Car successfully add");
        return CarMapper.INSTANCE.toDto(car);
    }

    @Override
    public CarDto update(String vin, CarDto carDto) {
        log.debug("Updating car by vin: {}", vin);
        Car car = findById(vin);
        car.setLicensePlate(carDto.getLicensePlate());
        carRepository.save(car);
        log.debug("Info updated for car: {}", vin);
        return CarMapper.INSTANCE.toDto(car);
    }

    @Override
    public List<CarDto> getAll() {
        log.debug("Getting all cars");
        return carRepository.findAll()
                .stream()
                .map(CarMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDto get(String vin) {
        log.debug("Getting car by vin: {}", vin);
        return CarMapper.INSTANCE.toDto(findById(vin));
    }

    @Override
    public void delete(String vin) {
        log.debug("Delete car by vin: {}", vin);
        Car car = findById(vin);
        carRepository.delete(car);
        log.info("Car deleted successfully");
    }

    @Override
    public boolean validCheck(CarDto carDto) {
        return carDto.getVin() != null && !carDto.getVin().isBlank()
                && carDto.getLicensePlate() != null && !carDto.getLicensePlate().isBlank()
                && carDto.getProducer() != null && !carDto.getProducer().isBlank()
                && carDto.getModel() != null && !carDto.getModel().isBlank()
                && carDto.getProductionYear() != null && carDto.getProductionYear() > 1980;
    }

    @Override
    public Car findById(String vin) {
        log.debug("Finding car by vin: {}", vin);
        return carRepository.findById(vin).orElseThrow(ItemNotFoundException::new);
    }

    //TODO: добавить логгер
    public void addDriver(String vin, Long driverId) {
        Car car = findById(vin);
        Driver driver = driverService.findById(driverId);
        car.setDriver(driver);
        carRepository.save(car);
    }
}