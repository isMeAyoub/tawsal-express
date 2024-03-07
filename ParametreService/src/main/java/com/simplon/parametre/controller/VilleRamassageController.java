package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import com.simplon.parametre.service.VilleRamassageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ville-ramassages")
public class VilleRamassageController {

    private final VilleRamassageService villeRamassageService;

    @PostMapping
    ResponseEntity<VilleRamassageResponseDto> createVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto){
        VilleRamassageResponseDto villeRamassageResponseDto = villeRamassageService.createVilleRamassage(villeRamassageRequestDto);
        return new ResponseEntity<>(villeRamassageResponseDto, HttpStatus.CREATED);
    }
}
