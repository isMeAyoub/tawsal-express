package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.request.TarifRequestDto;
import com.simplon.parametre.dtos.response.TarifResponseDto;
import com.simplon.parametre.model.entity.Tarif;
import org.mapstruct.*;

/**
 * Mapper for {@link com.simplon.parametre.model.entity.Tarif}
 * Contains the methods to convert the data
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TarifMapper {
    Tarif toEntity(TarifRequestDto tarifRequestDto);

    TarifRequestDto toDto(Tarif tarif);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tarif partialUpdate(TarifRequestDto tarifRequestDto, @MappingTarget Tarif tarif);

    Tarif toEntity(TarifResponseDto tarifResponseDto);

    TarifResponseDto toDto1(Tarif tarif);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tarif partialUpdate(TarifResponseDto tarifResponseDto, @MappingTarget Tarif tarif);
}