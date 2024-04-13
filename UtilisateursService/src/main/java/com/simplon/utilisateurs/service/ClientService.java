package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.dtos.response.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface to manage Client operations
 */
public interface ClientService {

    Page<ClientResponseDto> getValidclients(Pageable pageable);

    Page<ClientResponseDto> getInvalidClients(Pageable pageable);

    void createClient(ClientRequestDto clientRequestDto, MultipartFile photoProfile, MultipartFile photoCinRecto, MultipartFile photoCinVerso, MultipartFile photoRib);

    void updateClient(Long clientId, ClientRequestDto clientRequestDto, MultipartFile photoProfile, MultipartFile photoCinRecto, MultipartFile photoCinVerso, MultipartFile photoRib);

    void validateClient(Long clientId);

    void invalidateClient(Long clientId);

    void deleteClient(Long clientId);

    void suspendClient(Long clientId);

    ClientResponseDto getClient(Long clientId);
}
