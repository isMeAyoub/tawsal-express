package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {
    void createClient(ClientRequestDto clientRequestDto,
                      MultipartFile photoProfile,
                      MultipartFile photoCinRecto,
                      MultipartFile photoCinVerso,
                      MultipartFile photoRib);
}
