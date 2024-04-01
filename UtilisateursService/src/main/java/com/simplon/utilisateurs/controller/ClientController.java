package com.simplon.utilisateurs.controller;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.model.entity.Client;
import com.simplon.utilisateurs.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/utilisateurs/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(
            @Valid @RequestBody ClientRequestDto clientRequestDto,
            @RequestParam(value = "photoProfile") MultipartFile photoProfile,
            @RequestParam(value = "photoCinRecto") MultipartFile photoCinRecto,
            @RequestParam(value = "photoCinVerso") MultipartFile photoCinVerso,
            @RequestParam(value = "photoRib") MultipartFile photoRib
    ) {
        log.info("Creating client: {}", clientRequestDto);
        clientService.createClient(clientRequestDto, photoProfile, photoCinRecto, photoCinVerso, photoRib);
        log.info("Client created successfully");
    }
}
