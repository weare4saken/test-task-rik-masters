package com.wea4saken.rikmasters.service.impl;

import com.wea4saken.rikmasters.cryptograph.RandomSerialNumberGenerator;
import com.wea4saken.rikmasters.dto.DetailDto;
import com.wea4saken.rikmasters.exception.IncorrectArgumentException;
import com.wea4saken.rikmasters.exception.ItemNotFoundException;
import com.wea4saken.rikmasters.mapper.DetailMapper;
import com.wea4saken.rikmasters.model.Car;
import com.wea4saken.rikmasters.model.Detail;
import com.wea4saken.rikmasters.repository.DetailRepository;
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
public class DetailService implements CRUDService<DetailDto, String>, FindService<Detail, String> {

    private final DetailRepository detailRepository;
    private final CarService carService;

    @Override
    public DetailDto add(DetailDto detailDto) {
        log.debug("Adding detail");
        if(!validCheck(detailDto)) throw new IncorrectArgumentException();
        Detail detail = DetailMapper.INSTANCE.toEntity(detailDto);
        detail.setSerialNumber(RandomSerialNumberGenerator.generateSerialNumber());
        log.debug("Detail successfully add");
        return DetailMapper.INSTANCE.toDto(detail);
    }

    @Override
    public DetailDto update(String serialNumber, DetailDto detailDto) {
        log.debug("Updating detail by serialNumber: {}", serialNumber);
        Detail detail = findById(serialNumber);
        detail.setType(detailDto.getType());
        detailRepository.save(detail);
        log.debug("Info updated for detail: {}", serialNumber);
        return DetailMapper.INSTANCE.toDto(detail);
    }

    @Override
    public List<DetailDto> getAll() {
        log.debug("Getting all details");
        return detailRepository.findAll()
                .stream()
                .map(DetailMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetailDto get(String serialNumber) {
        log.debug("Getting detail by serialNumber: {}", serialNumber);
        return DetailMapper.INSTANCE.toDto(findById(serialNumber));
    }

    @Override
    public void delete(String serialNumber) {
        log.debug("Delete detail by serialNumber: {}", serialNumber);
        Detail detail = findById(serialNumber);
        detailRepository.delete(detail);
        log.info("Detail deleted successfully");
    }

    @Override
    public boolean validCheck(DetailDto detailDto) {
        log.debug("Validation check for detail");
        return detailDto.getType() != null && !detailDto.getType().isBlank();
    }

    @Override
    public Detail findById(String serialNumber) {
        log.debug("Finding detail by serialNumber: {}", serialNumber);
        return detailRepository.findById(serialNumber).orElseThrow(ItemNotFoundException::new);
    }

    public Detail findByType(String type) {
        log.debug("Finding detail by type: {}", type);
        return detailRepository.findByType(type).orElseThrow(ItemNotFoundException::new);
    }

    //TODO: добавить логгер
    public void addDetail(String vin, String detailType) {
        Car car = carService.findById(vin);
        Detail detail = findByType(detailType);
        detail.setCar(car);
        detailRepository.save(detail);
    }
}