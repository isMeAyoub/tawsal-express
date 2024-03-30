package com.simplon.demandes.service;

import com.simplon.demandes.dtos.request.DemandeRamassageRequestDto;
import com.simplon.demandes.dtos.response.DemandeRamassageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DemandeRamassageService {
    DemandeRamassageResponseDto createDemandeRamassage(DemandeRamassageRequestDto demandeRamassageRequestDto);
    DemandeRamassageResponseDto getDemandeRamassageById(Long id);
    Page<DemandeRamassageResponseDto> getAllDemandesRamassage(Pageable pageable);

    void deleteDemandeRamassageById(Long id);
    void makeDemandeRamassageAsTreated(Long id);
    void makeDemandeRamassageAsRead(Long id);
}
