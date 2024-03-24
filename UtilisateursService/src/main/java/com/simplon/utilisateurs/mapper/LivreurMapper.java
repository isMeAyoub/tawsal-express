package com.simplon.utilisateurs.mapper;

import com.simplon.utilisateurs.dtos.request.LivreurRequestDto;
import com.simplon.utilisateurs.model.entity.Livreur;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LivreurMapper {
    Livreur toEntity(LivreurRequestDto livreurRequestDto);

    LivreurRequestDto toDto(Livreur livreur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Livreur partialUpdate(LivreurRequestDto livreurRequestDto, @MappingTarget Livreur livreur);
}