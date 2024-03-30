package com.simplon.demandes.controller;

import com.simplon.demandes.dtos.request.DemandeRamassageRequestDto;
import com.simplon.demandes.dtos.response.DemandeRamassageResponseDto;
import com.simplon.demandes.service.DemandeRamassageService;
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

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/demandes/ramassage")
public class DemandeRamassageController {
    private final DemandeRamassageService demandeRamassageService;

    @PostMapping
    public ResponseEntity<DemandeRamassageResponseDto> createDemandeRamassage(@Valid @RequestBody DemandeRamassageRequestDto demandeRamassageRequestDto) {
        log.info("Creating a new demande ramassage");
        log.debug("Demande ramassage request: {}", demandeRamassageRequestDto);
        DemandeRamassageResponseDto demandeRamassage = demandeRamassageService.createDemandeRamassage(demandeRamassageRequestDto);
        log.debug("Demande ramassage created: {}", demandeRamassage);
        return new ResponseEntity<>(demandeRamassage, HttpStatus.CREATED);
    }

    @GetMapping("/{demandeRamassageId}")
    public ResponseEntity<DemandeRamassageResponseDto> getDemandeRamassageById(@PathVariable @NotNull Long demandeRamassageId) {
        log.info("Getting demande ramassage by id");
        log.debug("Demande ramassage id: {}", demandeRamassageId);
        DemandeRamassageResponseDto demandeRamassage = demandeRamassageService.getDemandeRamassageById(demandeRamassageId);
        log.debug("Demande ramassage found: {}", demandeRamassage);
        return new ResponseEntity<>(demandeRamassage, HttpStatus.OK);
    }

    @DeleteMapping("/{demandeRamassageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDemandeRamassage(@PathVariable @NotNull Long demandeRamassageId) {
        log.info("Deleting demande ramassage by id");
        log.debug("Demande ramassage id: {}", demandeRamassageId);
        demandeRamassageService.deleteDemandeRamassageById(demandeRamassageId);
    }

    @PatchMapping("/{demandeRamassageId}/treated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeDemandeRamassageAsTreated(@PathVariable @NotNull Long demandeRamassageId) {
        log.info("Making demande ramassage as treated");
        log.debug("Demande ramassage id: {}", demandeRamassageId);
        demandeRamassageService.makeDemandeRamassageAsTreated(demandeRamassageId);
    }

    @PatchMapping("/{demandeRamassageId}/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeDemandeRamassageAsRead(@PathVariable @NotNull Long demandeRamassageId) {
        log.info("Making demande ramassage as read");
        log.debug("Demande ramassage id: {}", demandeRamassageId);
        demandeRamassageService.makeDemandeRamassageAsRead(demandeRamassageId);
    }

    @GetMapping
    public ResponseEntity<Page<DemandeRamassageResponseDto>> getAllDemandesRamassage(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
            ) {
        log.info("Getting all demandes ramassage");
        log.debug("Page number: {}, page size: {}", page, size);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<DemandeRamassageResponseDto> demandesRamassage = demandeRamassageService.getAllDemandesRamassage(pageable);
        log.debug("Demandes ramassage found: {}", demandesRamassage);
        return new ResponseEntity<>(demandesRamassage, HttpStatus.OK);
    }
}
