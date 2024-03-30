package com.simplon.demandes.mapper;

import com.simplon.demandes.dtos.request.DemandeRamassageRequestDto;
import com.simplon.demandes.dtos.response.DemandeRamassageResponseDto;
import com.simplon.demandes.model.entity.DemandeRamassage;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DemandeRamassageMapper {
    DemandeRamassage toEntity(DemandeRamassageRequestDto demandeRamassageRequestDto);

    DemandeRamassageRequestDto toDto(DemandeRamassage demandeRamassage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DemandeRamassage partialUpdate(DemandeRamassageRequestDto demandeRamassageRequestDto, @MappingTarget DemandeRamassage demandeRamassage);

    DemandeRamassage toEntity(DemandeRamassageResponseDto demandeRamassageResponseDto);

    DemandeRamassageResponseDto toDto1(DemandeRamassage demandeRamassage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DemandeRamassage partialUpdate(DemandeRamassageResponseDto demandeRamassageResponseDto, @MappingTarget DemandeRamassage demandeRamassage);
}