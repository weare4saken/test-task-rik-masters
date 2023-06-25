package com.wea4saken.rikmasters.mapper;

import com.wea4saken.rikmasters.dto.DriverDto;
import com.wea4saken.rikmasters.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    DriverDto toDto(Driver driver);

    @Mapping(target = "balance", ignore = true)
    Driver toEntity(DriverDto dto);

}