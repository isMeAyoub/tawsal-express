package com.simplon.demandes.mapper;

import com.simplon.demandes.dtos.request.ReclamationsRequestDto;
import com.simplon.demandes.dtos.response.ReclamationsResponseDto;
import com.simplon.demandes.model.entity.Reclamations;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReclamationsMapper {
    Reclamations toEntity(ReclamationsRequestDto reclamationsRequestDto);

    ReclamationsRequestDto toDto(Reclamations reclamations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Reclamations partialUpdate(ReclamationsRequestDto reclamationsRequestDto, @MappingTarget Reclamations reclamations);

    Reclamations toEntity(ReclamationsResponseDto reclamationsResponseDto);

    ReclamationsResponseDto toDto1(Reclamations reclamations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Reclamations partialUpdate(ReclamationsResponseDto reclamationsResponseDto, @MappingTarget Reclamations reclamations);
}