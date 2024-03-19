package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This service layer interface defines business logic operations for Zone entities.
 * It provides methods for CRUD operations (Create, Read, Update, Delete)
 * and other relevant functionalities related to Zone management.
 */
public interface ZoneService {
    ZoneResponseDto createZone(ZoneRequestDto zoneRequestDto);

    ZoneResponseDto updateZone(Long zoneId, ZoneRequestDto zoneRequestDto);

    ZoneResponseDto getZone(Long zoneId);

    void deleteZone(Long zoneId);

    void changeZoneStatus(Long zoneId);

    Page<ZoneResponseDto> getAllZones(Pageable pageable, String search);
}
