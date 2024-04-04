package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.dtos.response.ClientResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {
    void createClient(ClientRequestDto clientRequestDto,
                      MultipartFile photoProfile,
                      MultipartFile photoCinRecto,
                      MultipartFile photoCinVerso,
                      MultipartFile photoRib);

    void updateClient(Long clientId,
                      ClientRequestDto clientRequestDto,
                      MultipartFile photoProfile,
                      MultipartFile photoCinRecto,
                      MultipartFile photoCinVerso,
                      MultipartFile photoRib);

    void validateClient(Long clientId);

    void invalidateClient(Long clientId);

    void deleteClient(Long clientId);

    void suspendClient(Long clientId);

    ClientResponseDto getClient(Long clientId);
}
