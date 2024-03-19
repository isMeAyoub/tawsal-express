package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;
import com.simplon.parametre.model.entity.Zone;
import org.mapstruct.*;

/**
 * Mapper for {@link com.simplon.parametre.model.entity.Zone}
 * Contains the methods to convert the data
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ZoneMapper {
    Zone toEntity(ZoneRequestDto zoneRequestDto);

    ZoneRequestDto toDto(Zone zone);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Zone partialUpdate(ZoneRequestDto zoneRequestDto, @MappingTarget Zone zone);

    Zone toEntity(ZoneResponseDto zoneResponseDto);

    @AfterMapping
    default void linkVillesLivraison(@MappingTarget Zone zone) {
        zone.getVillesLivraison().forEach(villesLivraison -> villesLivraison.setZone(zone));
    }

    ZoneResponseDto toDto1(Zone zone);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Zone partialUpdate(ZoneResponseDto zoneResponseDto, @MappingTarget Zone zone);
}