package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;

public interface ZoneService {
    ZoneResponseDto createZone(ZoneRequestDto zoneRequestDto);
}
