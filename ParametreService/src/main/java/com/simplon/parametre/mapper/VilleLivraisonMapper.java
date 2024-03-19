package com.simplon.parametre.mapper;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import com.simplon.parametre.model.entity.VilleLivraison;
import org.mapstruct.*;

/**
 * Mapper for {@link com.simplon.parametre.model.entity.VilleLivraison}
 * Contains the methods to convert the data
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VilleLivraisonMapper {
    VilleLivraison toEntity(VilleLivraisonRequestDto villeLivraisonRequestDto);

    VilleLivraisonRequestDto toDto(VilleLivraison villeLivraison);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VilleLivraison partialUpdate(VilleLivraisonRequestDto villeLivraisonRequestDto, @MappingTarget VilleLivraison villeLivraison);

    VilleLivraison toEntity(VilleLivraisonResponseDto villeLivraisonResponseDto);

    VilleLivraisonResponseDto toDto1(VilleLivraison villeLivraison);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VilleLivraison partialUpdate(VilleLivraisonResponseDto villeLivraisonResponseDto, @MappingTarget VilleLivraison villeLivraison);
}