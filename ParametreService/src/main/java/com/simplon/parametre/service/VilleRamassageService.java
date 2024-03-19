package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This service layer interface defines business logic operations for VilleRamassage entities.
 * It provides methods for CRUD operations (Create, Read, Update, Delete)
 * and other relevant functionalities related to VilleRamassage management.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
public interface VilleRamassageService {
    VilleRamassageResponseDto createVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto);

    Page<VilleRamassageResponseDto> getAllVilleRamassage(String search, Pageable pageable);
    VilleRamassageResponseDto getVilleRamassageById(Long villeId);

    VilleRamassageResponseDto updateVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto, Long villeId);

    void changeStatusOfVilleRamassage(Long villeId);

    void deleteVilleRamassageById(Long villeId);
}
