package com.simplon.media.mapper;

import com.simplon.media.dtos.response.FileResponseDto;
import com.simplon.media.model.entity.File;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileMapper {
    File toEntity(FileResponseDto fileResponseDto);

    FileResponseDto toDto(File file);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    File partialUpdate(FileResponseDto fileResponseDto, @MappingTarget File file);
}