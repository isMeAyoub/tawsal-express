package com.simplon.parametre.service.impl;

import com.simplon.parametre.model.entity.Zone;
import com.simplon.parametre.repository.ZoneRepository;
import com.simplon.parametre.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {
    /*

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;
    @Override
    public ZoneResponseDto createZone(ZoneRequestDto zoneRequestDto) {
        Zone zone = zoneMapper.toEntity(zoneRequestDto);
        if (zoneRepository.findByNomZoneIgnoreCase(zone.getNomZone()).isPresent()){
            throw new RuntimeException("Zone That you added already exists");
        }
        return zoneMapper.toDto1(zoneRepository.save(zone));
    }

    @Override
    public Page<ZoneResponseDto> getAllZone(Pageable pageable) {
        Page<Zone> zonePage = zoneRepository.findAll(pageable);
        Page<ZoneResponseDto> zoneResponseDtosPage = zonePage.map(zoneMapper::toDto1);
        return zoneResponseDtosPage;
    }

     */
}
