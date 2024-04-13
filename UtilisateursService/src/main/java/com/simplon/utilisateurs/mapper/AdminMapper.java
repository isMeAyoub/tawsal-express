package com.simplon.utilisateurs.mapper;

import com.simplon.utilisateurs.dtos.request.AdminRequestDto;
import com.simplon.utilisateurs.dtos.response.AdminResponseDto;
import com.simplon.utilisateurs.model.entity.Admin;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminMapper {
    Admin toEntity(AdminRequestDto adminRequestDto);

    AdminRequestDto toDto(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Admin partialUpdate(AdminRequestDto adminRequestDto, @MappingTarget Admin admin);

    Admin toEntity(AdminResponseDto adminResponseDto);

    AdminResponseDto toDto1(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Admin partialUpdate(AdminResponseDto adminResponseDto, @MappingTarget Admin admin);
}