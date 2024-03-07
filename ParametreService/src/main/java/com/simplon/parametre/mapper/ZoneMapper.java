package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.ZoneDto;
import com.simplon.parametre.model.entity.Zone;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ZoneMapper {
    Zone toEntity(ZoneDto zoneDto);

    @AfterMapping
    default void linkVillesLivraison(@MappingTarget Zone zone) {
        zone.getVillesLivraison().forEach(villesLivraison -> villesLivraison.setZone(zone));
    }

    ZoneDto toDto(Zone zone);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Zone partialUpdate(ZoneDto zoneDto, @MappingTarget Zone zone);
}