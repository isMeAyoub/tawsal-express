package com.simplon.utilisateurs.controller;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.dtos.response.ClientResponseDto;
import com.simplon.utilisateurs.model.entity.Client;
import com.simplon.utilisateurs.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("invalid")
    public ResponseEntity<Page<ClientResponseDto>> getInvalidClients(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        log.info("Getting invalid clients");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ClientResponseDto> clients = clientService.getInvalidClients(pageable);
        log.info("Invalid clients found: {}", clients);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("valid")
    public ResponseEntity<Page<ClientResponseDto>> getValidClients(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        log.info("Getting valid clients");
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientResponseDto> clients = clientService.getValidclients(pageable);
        log.info("Valid clients found: {}", clients);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

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

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> getClient(@PathVariable @NotNull Long clientId) {
        log.info("Getting client with id: {}", clientId);
        ClientResponseDto client = clientService.getClient(clientId);
        log.info("Client found: {}", client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable @NotNull Long clientId) {
        log.info("Deleting client with id: {}", clientId);
        clientService.deleteClient(clientId);
        log.info("Client deleted successfully");
    }

    @PatchMapping("validate/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validateClient(@PathVariable @NotNull Long clientId) {
        log.info("Validating client with id: {}", clientId);
        clientService.validateClient(clientId);
        log.info("Client validated successfully");
    }

    @PatchMapping("invalidate/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invalidateClient(@PathVariable @NotNull Long clientId) {
        log.info("Invalidating client with id: {}", clientId);
        clientService.invalidateClient(clientId);
        log.info("Client invalidated successfully");
    }

    @PatchMapping("suspend/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void suspendClient(@PathVariable @NotNull Long clientId) {
        log.info("Suspending client with id: {}", clientId);
        clientService.suspendClient(clientId);
        log.info("Client suspended successfully");
    }


}
