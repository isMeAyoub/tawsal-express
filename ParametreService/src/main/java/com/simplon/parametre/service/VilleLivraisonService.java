package com.simplon.parametre.service;

import com.simplon.parametre.dtos.VilleLivraisonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleLivraisonService {
    VilleLivraisonDto getVilleLivraisonById(Long villeId);
    Page<VilleLivraisonDto> getAllVilleLivraison(Pageable pageable);
    VilleLivraisonDto createVilleLivraison(VilleLivraisonDto villeLivraisonDto);

}
