package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneClientResponseDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;
import com.simplon.parametre.mapper.ZoneMapper;
import com.simplon.parametre.model.entity.Zone;
import com.simplon.parametre.repository.ZoneRepository;
import com.simplon.parametre.service.ZoneService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Implementation of {@link ZoneService}
 * Contains the business logic to process the data
 *
 * @Author: Ayoub Ait Si Ahmad
 */
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
        zone.setIsActive(true);
        zone.setVillesLivraison(new ArrayList<>());
        Zone savedZone = zoneRepository.save(zone);
        ZoneResponseDto zoneResponseDto = zoneMapper.toDto1(savedZone);
        log.info("Zone with name {} created successfully", zone.getNomZone());
        return zoneResponseDto;
    }

    @Override
    public ZoneResponseDto updateZone(Long zoneId, ZoneRequestDto zoneRequestDto) {
        log.info("Updating zone with id {}", zoneId);
        Zone zone = findZoneById(zoneId);
        checkIfZoneNameExists(zoneRequestDto.getNomZone(), zoneId);
        Zone savedZone = zoneRepository.save(zoneMapper.partialUpdate(zoneRequestDto, zone));
        log.info("Zone with id {} updated successfully", zoneId);
        return zoneMapper.toDto1(savedZone);
    }

    private void checkIfZoneNameExists(String zoneName, Long zoneId) {
        zoneRepository.findByNomZoneAndZoneIdNot(zoneName, zoneId)
                .ifPresent(zone -> {
                    log.error("Zone with name {} already exists", zoneName);
                    throw new EntityExistsException("Zone with name " + zoneName + " already exists");
                });
    }

    @Override
    public ZoneResponseDto getZone(Long zoneId) {
        log.info("Fetching zone with id {}", zoneId);
        Zone zone = findZoneById(zoneId);
        ZoneResponseDto zoneResponseDto = zoneMapper.toDto1(zone);
        log.info("Zone with id {} fetched successfully", zoneId);
        return zoneResponseDto;
    }

    @Override
    public ZoneClientResponseDto getZoneToClient(Long zoneId) {
        log.info("Fetching zone with id {}", zoneId);
        Zone zone = findZoneById(zoneId);
        ZoneClientResponseDto zoneClientResponseDto = zoneMapper.toDto2(zone);
        log.info("Zone with id {} fetched successfully", zoneId);
        return zoneClientResponseDto;
    }

    @Override
    public void deleteZone(Long zoneId) {
        log.info("Deleting zone with id {}", zoneId);
        Zone zone = findZoneById(zoneId);
        zoneRepository.deleteById(zoneId);
        log.info("Zone with id {} deleted successfully", zoneId);
    }

    @Override
    public void changeZoneStatus(Long zoneId) {
        log.info("Changing status of zone with id {}", zoneId);
        Zone zone = findZoneById(zoneId);
        zone.setIsActive(!zone.getIsActive());
        zoneRepository.save(zone);
        log.info("Status of zone with id {} changed successfully", zoneId);
    }

    @Override
    public Page<ZoneResponseDto> getAllZones(Pageable pageable,String search) {
        log.info("Fetching all zones");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<Zone> zones = zoneRepository.findAllByNomZone(sortedPageable, search);
        Page<ZoneResponseDto> zoneResponseDtos = zones.map(zoneMapper::toDto1);
        log.info("All zones fetched successfully");
        return zoneResponseDtos;
    }

    private Zone findZoneById(Long zoneId) {
        log.info("Fetching zone with id {}", zoneId);
        return zoneRepository.findById(zoneId).orElseThrow(() -> entityNotFoundException(zoneId));
    }

    private EntityNotFoundException entityNotFoundException(Long zoneId) {
        log.error("Zone with id {} not found", zoneId);
        return new EntityNotFoundException("Zone with id " + zoneId + " not found");
    }
}