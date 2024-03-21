package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.TarifRequestDto;
import com.simplon.parametre.dtos.response.TarifResponseDto;
import com.simplon.parametre.service.TarifService;
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
 * THis class is the controller of the {@link com.simplon.parametre.model.entity.Tarif} entity
 * It allows to manage the Tarif entity
 *
 * @Author:: Ayoub Ait SI Ahmad
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/parametre/tarifs")
@RequiredArgsConstructor
public class TarifController {

    private final TarifService tarifService;

    @GetMapping
    public ResponseEntity<Page<TarifResponseDto>> getAllTarifs(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "VilleRamassage", required = false, defaultValue = "") String VilleRamassage,
            @RequestParam(value = "VilleLivraison", required = false, defaultValue = "") String VilleLivraison
    ) {
        log.info("Getting all Tarifs");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<TarifResponseDto> tarifResponseDto = tarifService.getAllTarifs(pageable, search, VilleRamassage, VilleLivraison);
        log.info("Tarifs found successfully : {}", tarifResponseDto);
        return new ResponseEntity<>(tarifResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{zoneId}")
    public ResponseEntity<TarifResponseDto> updateTarif(@NotNull @PathVariable Long zoneId, @Valid @RequestBody TarifRequestDto tarifRequestDto) {
        log.info("Updating Tarif with zoneId : {}", zoneId);
        TarifResponseDto tarifResponseDto = tarifService.updateTarif(zoneId, tarifRequestDto);
        log.info("Tarif updated successfully : {}", tarifResponseDto);
        return ResponseEntity.ok(tarifResponseDto);
    }

    @PostMapping
    public ResponseEntity<TarifResponseDto> createTarif(@Valid @RequestBody TarifRequestDto tarifRequestDto) {
        log.info("Creating a new Tarif : {}", tarifRequestDto);
        TarifResponseDto tarifResponseDto = tarifService.createTarif(tarifRequestDto);
        log.info("Tarif created successfully : {}", tarifResponseDto);
        return ResponseEntity.ok(tarifResponseDto);
    }

    @GetMapping("/{zoneId}")
    public ResponseEntity<TarifResponseDto> getTarifByZoneId(@NotNull @PathVariable Long zoneId) {
        log.info("Getting Tarif by zoneId : {}", zoneId);
        TarifResponseDto tarifResponseDto = tarifService.getTarifById(zoneId);
        log.info("Tarif found successfully : {}", tarifResponseDto);
        return ResponseEntity.ok(tarifResponseDto);
    }

    @DeleteMapping("/{zoneId}")
    public ResponseEntity<Void> deleteTarifByZoneId(@NotNull @PathVariable Long zoneId) {
        log.info("Deleting Tarif by zoneId : {}", zoneId);
        tarifService.deleteTarif(zoneId);
        log.info("Tarif deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
