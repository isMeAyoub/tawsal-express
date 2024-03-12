package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;
import com.simplon.parametre.service.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/zones")
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    ResponseEntity<ZoneResponseDto> createZone(@Valid @RequestBody ZoneRequestDto zoneRequestDto) {
        log.info("Request received to create Zone: {}", zoneRequestDto);
        ZoneResponseDto zoneResponseDto = zoneService.createZone(zoneRequestDto);
        log.info("Zone created successfully: {}", zoneResponseDto);
        return ResponseEntity.ok(zoneResponseDto);
    }
}
