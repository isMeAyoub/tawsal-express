package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import com.simplon.parametre.mapper.VilleLivraisonMapper;
import com.simplon.parametre.model.entity.VilleLivraison;
import com.simplon.parametre.repository.VilleLivraisonRepository;
import com.simplon.parametre.service.VilleLivraisonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VilleLivraisonServiceImpl implements VilleLivraisonService {

    private final VilleLivraisonRepository villeLivraisonRepository;
    private final VilleLivraisonMapper villeLivraisonMapper;

    @Override
    public VilleLivraisonResponseDto createVilleLivraison(VilleLivraisonRequestDto villeLivraisonRequestDto) {
        VilleLivraison villeLivraison = villeLivraisonMapper.toEntity(villeLivraisonRequestDto);
        villeLivraison.setIsActive(true);
        VilleLivraison savedVilleLivraison = villeLivraisonRepository.save(villeLivraison);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonMapper.toDto1(savedVilleLivraison);
        return villeLivraisonResponseDto;
    }
}
