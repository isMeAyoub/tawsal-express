package com.simplon.media.mapper;

import com.simplon.media.dtos.response.FileDataResponseDto;
import com.simplon.media.model.entity.FileData;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileDataMapper {
    FileData toEntity(FileDataResponseDto fileDataResponseDto);

    FileDataResponseDto toDto(FileData fileData);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FileData partialUpdate(FileDataResponseDto fileDataResponseDto, @MappingTarget FileData fileData);
}