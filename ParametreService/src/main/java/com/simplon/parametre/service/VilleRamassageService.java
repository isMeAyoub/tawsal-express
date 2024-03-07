package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VilleRamassageService {
    VilleRamassageResponseDto createVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto);
    Page<VilleRamassageResponseDto> getAllVilleRamassage(Pageable pageable);
}
