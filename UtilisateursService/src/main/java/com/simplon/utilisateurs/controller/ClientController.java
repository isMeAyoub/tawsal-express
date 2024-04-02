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
            @Valid @RequestPart("client") ClientRequestDto client,
            @RequestPart(value = "photoProfile") MultipartFile photoProfile,
            @RequestPart(value = "photoCinRecto") MultipartFile photoCinRecto,
            @RequestPart(value = "photoCinVerso") MultipartFile photoCinVerso,
            @RequestPart(value = "photoRib") MultipartFile photoRib
    ) {
        log.info("Creating client: {}", client);
        clientService.createClient(client, photoProfile, photoCinRecto, photoCinVerso, photoRib);
        log.info("Client created successfully");
    }

}
