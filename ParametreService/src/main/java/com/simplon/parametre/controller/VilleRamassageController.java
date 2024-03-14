package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import com.simplon.parametre.service.VilleRamassageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
 * This controller class handles API requests related to VilleRamassage entities.
 * It provides endpoints .
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parametre/ville-ramassages")
public class VilleRamassageController {

    private final VilleRamassageService villeRamassageService;

    @PostMapping
    public ResponseEntity<VilleRamassageResponseDto> createVilleRamassage(@RequestBody @Valid VilleRamassageRequestDto villeRamassageRequestDto) {
        log.info("Request received to create VilleRamassage: {}", villeRamassageRequestDto);
        VilleRamassageResponseDto villeRamassageResponseDto = villeRamassageService.createVilleRamassage(villeRamassageRequestDto);
        log.info("VilleRamassage created successfully: {}", villeRamassageResponseDto);
        return new ResponseEntity<>(villeRamassageResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<VilleRamassageResponseDto>> getAllVilleRamassage(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        log.info("Request received to get all VilleRamassage (page: {}, size: {}, search: {})", page, size, search);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<VilleRamassageResponseDto> villeRamassageResponseDto = villeRamassageService.getAllVilleRamassage(search, pageable);
        log.info("Retrieved {} VilleRamassage entities", villeRamassageResponseDto.getTotalElements());
        return new ResponseEntity<>(villeRamassageResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{villeId}")
    public ResponseEntity<VilleRamassageResponseDto> updateVilleRamassage(@NotNull @PathVariable Long villeId, @Valid @RequestBody VilleRamassageRequestDto villeRamassageRequestDto) {
        log.info("Request received to update VilleRamassage with ID: {}", villeId);
        VilleRamassageResponseDto updatedVilleRamassage = villeRamassageService.updateVilleRamassage(villeRamassageRequestDto, villeId);
        log.info("VilleRamassage with ID {} updated successfully", villeId);
        return new ResponseEntity<>(updatedVilleRamassage, HttpStatus.OK);
    }

    @PatchMapping("/{villeId}/status")
    public ResponseEntity<Void> changeStatusOfVilleRamassage(@NotNull @PathVariable Long villeId) {
        log.info("Request received to change status of VilleRamassage with ID: {}", villeId);
        villeRamassageService.changeStatusOfVilleRamassage(villeId);
        log.info("Status of VilleRamassage with ID: {} changed successfully", villeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{villeId}")
    public ResponseEntity<Void> deleteVilleRamassageById(@NotNull @PathVariable Long villeId) {
        log.info("Request received to delete VilleRamassage with ID: {}", villeId);
        villeRamassageService.deleteVilleRamassageById(villeId);
        log.info("VilleRamassage with ID: {} deleted successfully", villeId);
        return ResponseEntity.noContent().build();
    }
}
