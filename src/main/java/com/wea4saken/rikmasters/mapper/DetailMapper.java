package com.wea4saken.rikmasters.mapper;

import com.wea4saken.rikmasters.dto.DetailDto;
import com.wea4saken.rikmasters.model.Detail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetailMapper {

    DetailMapper INSTANCE = Mappers.getMapper(DetailMapper.class);

    DetailDto toDto(Detail detail);

    @Mapping(target = "serialNumber", ignore = true)
    Detail toEntity(DetailDto dto);

}