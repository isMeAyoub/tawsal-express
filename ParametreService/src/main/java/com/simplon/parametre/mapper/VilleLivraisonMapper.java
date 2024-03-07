package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.VilleLivraisonDto;
import com.simplon.parametre.model.entity.VilleLivraison;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VilleLivraisonMapper {
    VilleLivraison toEntity(VilleLivraisonDto villeLivraisonDto);

    VilleLivraisonDto toDto(VilleLivraison villeLivraison);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VilleLivraison partialUpdate(VilleLivraisonDto villeLivraisonDto, @MappingTarget VilleLivraison villeLivraison);
}