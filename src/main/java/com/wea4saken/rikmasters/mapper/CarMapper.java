package com.wea4saken.rikmasters.mapper;

import com.wea4saken.rikmasters.dto.CarDto;
import com.wea4saken.rikmasters.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto toDto(Car car);

    @Mapping(target = "details", ignore = true)
    @Mapping(target = "driver", ignore = true)
    Car toEntity(CarDto dto);

}