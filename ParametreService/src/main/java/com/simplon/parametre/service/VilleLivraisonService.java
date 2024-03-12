package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;

/**
 * This interface contains methods for managing {@link com.simplon.parametre.model.entity.VilleLivraison}
 *
 * @Author: Ayoub Ait Si Ahmad
 */
public interface VilleLivraisonService {
    VilleLivraisonResponseDto createVilleLivraison(VilleLivraisonRequestDto villeLivraisonRequestDto);
}
