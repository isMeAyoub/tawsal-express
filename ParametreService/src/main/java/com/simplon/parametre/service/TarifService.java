package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.TarifRequestDto;
import com.simplon.parametre.dtos.response.TarifResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This service layer interface defines business logic operations for Tarif entities.
 * It provides methods for CRUD operations (Create, Read, Update, Delete)
 * and other relevant functionalities related to Tarif management.
 *
 * @Ayoub: Ayoub Ait Si Ahmad
 **/

public interface TarifService {
    TarifResponseDto createTarif(TarifRequestDto tarifRequestDto);

    TarifResponseDto updateTarif(Long id, TarifRequestDto tarifRequestDto);

    Page<TarifResponseDto> getAllTarifs(Pageable pageable, String search, String VilleRamassage, String VilleLivraison);

    TarifResponseDto getTarifById(Long id);

    void deleteTarif(Long id);
}
