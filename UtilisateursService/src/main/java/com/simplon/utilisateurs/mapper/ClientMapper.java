package com.simplon.utilisateurs.mapper;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.model.entity.Client;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toEntity(ClientRequestDto clientRequestDto);

    ClientRequestDto toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientRequestDto clientRequestDto, @MappingTarget Client client);
}