package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import com.simplon.parametre.service.VilleLivraisonService;
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
@RequestMapping("/api/v1/parametre/ville-livraisons")
@RequiredArgsConstructor
public class VilleLivraisonController {

    private final VilleLivraisonService villeLivraisonService;

    @PostMapping
    ResponseEntity<VilleLivraisonResponseDto> createVilleLivraison(@Valid @RequestBody VilleLivraisonRequestDto villeLivraisonRequestDto) {
        log.info("Request received to create VilleLivraison: {}", villeLivraisonRequestDto);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonService.createVilleLivraison(villeLivraisonRequestDto);
        log.info("VilleLivraison created successfully: {}", villeLivraisonResponseDto);
        return ResponseEntity.ok(villeLivraisonResponseDto);
    }
}
