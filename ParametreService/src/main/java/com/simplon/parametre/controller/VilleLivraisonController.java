package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import com.simplon.parametre.service.VilleLivraisonService;
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
 * This class is the controller of VilleLivraison entity and it contains all the endpoints related to VilleLivraison entity.
 * It provides endpoints .
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/parametre/ville-livraisons")
@RequiredArgsConstructor
public class VilleLivraisonController {

    private final VilleLivraisonService villeLivraisonService;

    @PostMapping
    ResponseEntity<VilleLivraisonResponseDto> createVilleLivraison(@Valid @RequestBody VilleLivraisonRequestDto villeLivraisonRequestDto) {
        log.info("Request received to create VilleLivraison: {}", villeLivraisonRequestDto);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonService.createVilleLivraison(villeLivraisonRequestDto);
        log.info("VilleLivraison created successfully: {}", villeLivraisonResponseDto);
        return new ResponseEntity<>(villeLivraisonResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<VilleLivraisonResponseDto> getByIdVilleLivraison(@PathVariable @NotNull Long id) {
        log.info("Request received to get VilleLivraison by id: {}", id);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonService.getByIdVilleLivraison(id);
        log.info("VilleLivraison retrieved successfully: {}", villeLivraisonResponseDto);
        return ResponseEntity.ok(villeLivraisonResponseDto);
    }

    @GetMapping
    ResponseEntity<Page<VilleLivraisonResponseDto>> getAllVilleLivraison(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        log.info("Request received to get all VilleLivraison (page: {}, size: {})", page, size);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<VilleLivraisonResponseDto> villeLivraisonResponseDto = villeLivraisonService.getAllVilleLivraison(pageable, search);
        log.info("Retrieved {} VilleLivraison entities", villeLivraisonResponseDto.getTotalElements());
        return new ResponseEntity<>(villeLivraisonResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteVilleLivraison(@PathVariable @NotNull Long id) {
        log.info("Request received to delete VilleLivraison with ID: {}", id);
        villeLivraisonService.deleteVilleLivraison(id);
        log.info("VilleLivraison with ID {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<VilleLivraisonResponseDto> updateVilleLivraison(@PathVariable @NotNull Long id, @Valid @RequestBody VilleLivraisonRequestDto villeLivraisonRequestDto) {
        log.info("Request received to update VilleLivraison with ID: {}", id);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonService.updateVilleLivraison(id, villeLivraisonRequestDto);
        log.info("VilleLivraison with ID {} updated successfully", id);
        return new ResponseEntity<>(villeLivraisonResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> changeStatusOfVilleLivraison(@PathVariable @NotNull Long id) {
        log.info("Request received to changeStatus of VilleLivraison with ID: {}", id);
        villeLivraisonService.changeStatusOfVilleLivraison(id);
        log.info("VilleLivraison with ID {} ChangeStatus successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}