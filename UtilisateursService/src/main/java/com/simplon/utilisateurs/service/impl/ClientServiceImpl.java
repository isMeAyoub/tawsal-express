package com.simplon.utilisateurs.service.impl;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.model.entity.Client;
import com.simplon.utilisateurs.repository.ClientRepository;
import com.simplon.utilisateurs.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public void createClient(ClientRequestDto clientRequestDto,
                             MultipartFile photoProfile,
                             MultipartFile photoCinRecto,
                             MultipartFile photoCinVerso,
                             MultipartFile photoRib) {
        log.info("Creating client: {}", clientRequestDto);
        // TODO: check if the client already exists
        // TODO: save the photos using media service
        // TODO: email verification
        // TODO: add the client to the database
        // TODO: add the client to keycloak
        // TODO: send notification to the admin
    }

    @Override
    public void updateClient(Long clientId, ClientRequestDto clientRequestDto, MultipartFile photoProfile, MultipartFile photoCinRecto, MultipartFile photoCinVerso, MultipartFile photoRib) {

    }

    @Override
    public void validateClient(Long clientId) {

    }

    @Override
    public void invalidateClient(Long clientId) {

    }

    @Override
    public void deleteClient(Long clientId) {

    }

}
