package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;
import com.simplon.parametre.model.entity.General;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GeneralMapper {
    General toEntity(GeneralRequestDto generalRequestDto);

    GeneralRequestDto toDto(General general);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    General partialUpdate(GeneralRequestDto generalRequestDto, @MappingTarget General general);

    General toEntity(GeneralResponseDto generalResponseDto);

    GeneralResponseDto toDto1(General general);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    General partialUpdate(GeneralResponseDto generalResponseDto, @MappingTarget General general);
}