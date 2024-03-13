package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.ZoneRequestDto;
import com.simplon.parametre.dtos.response.ZoneResponseDto;
import com.simplon.parametre.service.ZoneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for {@link com.simplon.parametre.model.entity.Zone}
 * Contains the endpoints to process the data
 *
 * @Author: Ayoub Ait Si Ahmad
 */
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
        return new ResponseEntity<>(zoneResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{zoneId}")
    ResponseEntity<ZoneResponseDto> getZone(@NotNull @PathVariable Long zoneId) {
        log.info("Request received to get Zone with id: {}", zoneId);
        ZoneResponseDto zoneResponseDto = zoneService.getZone(zoneId);
        log.info("Zone retrieved successfully: {}", zoneResponseDto);
        return ResponseEntity.ok(zoneResponseDto);
    }

    @GetMapping
    ResponseEntity<Page<ZoneResponseDto>> getAllZones(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("Request received to get all Zones");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ZoneResponseDto> zoneResponseDtoPage = zoneService.getAllZones(pageable);
        log.info("Zones retrieved successfully");
        return ResponseEntity.ok(zoneResponseDtoPage);
    }

    @PatchMapping("/{zoneId}")
    ResponseEntity<Void> changeZoneStatus(@NotNull @PathVariable Long zoneId) {
        log.info("Request received to change status of Zone with id: {}", zoneId);
        zoneService.changeZoneStatus(zoneId);
        log.info("Zone status changed successfully");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{zoneId}")
    ResponseEntity<ZoneResponseDto> updateZone(
            @NotNull @PathVariable Long zoneId,
            @Valid @RequestBody ZoneRequestDto zoneRequestDto) {
        log.info("Request received to update Zone with id: {}", zoneId);
        ZoneResponseDto zoneResponseDto = zoneService.updateZone(zoneId, zoneRequestDto);
        log.info("Zone updated successfully: {}", zoneResponseDto);
        return ResponseEntity.ok(zoneResponseDto);
    }

    @DeleteMapping("/{zoneId}")
    ResponseEntity<Void> deleteZone(@NotNull @PathVariable Long zoneId) {
        log.info("Request received to delete Zone with id: {}", zoneId);
        zoneService.deleteZone(zoneId);
        log.info("Zone deleted successfully");
        return ResponseEntity.noContent().build();
    }
}

