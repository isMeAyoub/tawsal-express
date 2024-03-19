package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import com.simplon.parametre.model.entity.VilleRamassage;
import org.mapstruct.*;

/**
 * Mapper for {@link com.simplon.parametre.model.entity.VilleRamassage}
 * Contains the methods to convert the data
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VilleRamassageMapper {
    VilleRamassage toEntity(VilleRamassageRequestDto villeRamassageRequestDto);

    VilleRamassageRequestDto toDto(VilleRamassage villeRamassage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VilleRamassage partialUpdate(VilleRamassageRequestDto villeRamassageRequestDto, @MappingTarget VilleRamassage villeRamassage);

    VilleRamassage toEntity(VilleRamassageResponseDto villeRamassageResponseDto);

    VilleRamassageResponseDto toDto1(VilleRamassage villeRamassage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VilleRamassage partialUpdate(VilleRamassageResponseDto villeRamassageResponseDto, @MappingTarget VilleRamassage villeRamassage);
}