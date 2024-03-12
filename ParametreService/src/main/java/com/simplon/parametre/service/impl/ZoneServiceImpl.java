package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;
import com.simplon.parametre.mapper.ZoneMapper;
import com.simplon.parametre.model.entity.Zone;
import com.simplon.parametre.repository.ZoneRepository;
import com.simplon.parametre.service.ZoneService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneMapper zoneMapper;
    private final ZoneRepository zoneRepository;
    @Override
    public ZoneResponseDto createZone(ZoneRequestDto zoneRequestDto) {
        log.info("Creating a new zone");
        Zone zone = zoneMapper.toEntity(zoneRequestDto);
        Optional<Zone> zoneOptional = zoneRepository.findByNomZoneIgnoreCase(zone.getNomZone());
        if (zoneOptional.isPresent()) {
            log.error("Zone with name {} already exists", zone.getNomZone());
            throw new EntityExistsException("Zone with name " + zone.getNomZone() + " already exists");
        }
        Zone savedZone = zoneRepository.save(zone);
        ZoneResponseDto zoneResponseDto = zoneMapper.toDto1(savedZone);
        log.info("Zone with name {} created successfully", zone.getNomZone());
        return zoneResponseDto;
    }
}
