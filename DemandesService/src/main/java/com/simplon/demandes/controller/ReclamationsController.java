package com.simplon.demandes.controller;

import com.simplon.demandes.dtos.request.ReclamationsRequestDto;
import com.simplon.demandes.dtos.response.ReclamationsResponseDto;
import com.simplon.demandes.service.ReclamationsService;
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
@RequestMapping("/api/v1/demandes/reclamation")
public class ReclamationsController {
    private final ReclamationsService reclamationsService;

    @PostMapping
    public ResponseEntity<ReclamationsResponseDto> createReclamation(@Valid @RequestBody ReclamationsRequestDto reclamationsRequestDto) {
        log.info("Creating a new reclamation");
        log.debug("Reclamation request: {}", reclamationsRequestDto);
        ReclamationsResponseDto reclamation = reclamationsService.createReclamation(reclamationsRequestDto);
        log.debug("Reclamation created: {}", reclamation);
        return new ResponseEntity<>(reclamation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ReclamationsResponseDto>> getAllReclamations(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
            ) {
        log.info("Getting all reclamations");
        log.debug("Page number: {}, page size: {}", page, size);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ReclamationsResponseDto> reclamations = reclamationsService.getAllReclamations(pageable);
        log.debug("Reclamations found: {}", reclamations);
        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }

    @GetMapping("/{reclamationId}")
    public ResponseEntity<ReclamationsResponseDto> getReclamationById(@NotNull @PathVariable Long reclamationId) {
        log.info("Getting reclamation by id");
        log.debug("Reclamation id: {}", reclamationId);
        ReclamationsResponseDto reclamation = reclamationsService.getReclamationById(reclamationId);
        log.debug("Reclamation found: {}", reclamation);
        return new ResponseEntity<>(reclamation, HttpStatus.OK);
    }

    @DeleteMapping("/{reclamationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReclamation(@NotNull @PathVariable Long reclamationId) {
        log.info("Deleting reclamation by id");
        log.debug("Reclamation id: {}", reclamationId);
        reclamationsService.deleteReclamation(reclamationId);
        log.debug("Reclamation deleted");
    }

    @PatchMapping("/{reclamationId}/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeReclamationAsRead(@NotNull @PathVariable Long reclamationId) {
        log.info("Making reclamation as read by id");
        log.debug("Reclamation id: {}", reclamationId);
        reclamationsService.makeReclamationAsRead(reclamationId);
        log.debug("Reclamation made as read");
    }

    @PatchMapping("/{reclamationId}/treated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeReclamationAsTreated(@NotNull @PathVariable Long reclamationId) {
        log.info("Making reclamation as treated by id");
        log.debug("Reclamation id: {}", reclamationId);
        reclamationsService.makeReclamationAsTreated(reclamationId);
        log.debug("Reclamation made as treated");
    }
}
