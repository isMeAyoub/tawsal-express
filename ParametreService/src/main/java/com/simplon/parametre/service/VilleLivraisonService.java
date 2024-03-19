package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface contains methods for managing {@link com.simplon.parametre.model.entity.VilleLivraison}
 *
 * @Author: Ayoub Ait Si Ahmad
 */
public interface VilleLivraisonService {
    VilleLivraisonResponseDto createVilleLivraison(VilleLivraisonRequestDto villeLivraisonRequestDto);
    VilleLivraisonResponseDto getByIdVilleLivraison(Long id);
    VilleLivraisonResponseDto updateVilleLivraison(Long id, VilleLivraisonRequestDto villeLivraisonRequestDto);
    void deleteVilleLivraison(Long id);
    Page<VilleLivraisonResponseDto> getAllVilleLivraison(Pageable pageable , String search);
    void changeStatusOfVilleLivraison(Long id);
}
