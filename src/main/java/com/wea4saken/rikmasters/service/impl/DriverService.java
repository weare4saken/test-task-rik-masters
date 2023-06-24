package com.wea4saken.rikmasters.service.impl;

import com.wea4saken.rikmasters.dto.DriverDto;
import com.wea4saken.rikmasters.exception.IncorrectArgumentException;
import com.wea4saken.rikmasters.exception.ItemNotFoundException;
import com.wea4saken.rikmasters.mapper.DriverMapper;
import com.wea4saken.rikmasters.model.Driver;
import com.wea4saken.rikmasters.repository.DriverRepository;
import com.wea4saken.rikmasters.service.CRUDService;
import com.wea4saken.rikmasters.service.FindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DriverService implements CRUDService<DriverDto, Long>, FindService<Driver, Long> {

    private final DriverRepository driverRepository;

    @Override
    public DriverDto add(DriverDto driverDto) {
        log.debug("Adding driver");
        if(!validCheck(driverDto)) throw new IncorrectArgumentException();
        Driver driver = DriverMapper.INSTANCE.toEntity(driverDto);
        driverRepository.save(driver);
        log.debug("Driver successfully add");
        return DriverMapper.INSTANCE.toDto(driver);
    }

    @Override
    public DriverDto update(Long id, DriverDto driverDto) {
        log.debug("Updating driver by id: {}", id);
        Driver driver = findById(id);
        driver.setFullName(driverDto.getFullName());
        driver.setPassportData(driverDto.getPassportData());
        driver.setPassportData(driverDto.getPassportData());
        driver.setLicenseCategory(driverDto.getLicenseCategory());
        driver.setExperience(driverDto.getExperience());
        driverRepository.save(driver);
        log.debug("Info updated for car: {}", id);
        return DriverMapper.INSTANCE.toDto(driver);
    }

    @Override
    public List<DriverDto> getAll(Integer pageNumber, Integer pageSize) {
        log.debug("Getting all drivers");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id"));
        return driverRepository.findAll(pageRequest)
                .stream()
                .map(DriverMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDto get(Long id) {
        log.debug("Getting driver by id: {}", id);
        return DriverMapper.INSTANCE.toDto(findById(id));
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete driver by id: {}", id);
        Driver driver = findById(id);
        driverRepository.delete(driver);
        log.info("Driver deleted successfully");
    }

    @Override
    public boolean validCheck(DriverDto driverDto) {
        return driverDto.getFullName() != null && !driverDto.getFullName().isBlank()
                && driverDto.getPassportData() != null && !driverDto.getPassportData().isBlank()
                && driverDto.getLicenseCategory() != null && driverDto.getLicenseCategory().isBlank()
                && driverDto.getBirthDate() != null && driverDto.getBirthDate().isAfter(Instant.now())
                && driverDto.getExperience() != null;
    }

    @Override
    public Driver findById(Long id) {
        log.debug("Finding car by vin: {}", id);
        return driverRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }
}