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
    }
}
