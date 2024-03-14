package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import com.simplon.parametre.mapper.VilleLivraisonMapper;
import com.simplon.parametre.model.entity.VilleLivraison;
import com.simplon.parametre.model.entity.Zone;
import com.simplon.parametre.repository.VilleLivraisonRepository;
import com.simplon.parametre.service.VilleLivraisonService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VilleLivraisonServiceImpl implements VilleLivraisonService {

    private final VilleLivraisonRepository villeLivraisonRepository;
    private final VilleLivraisonMapper villeLivraisonMapper;

    @Override
    public VilleLivraisonResponseDto createVilleLivraison(VilleLivraisonRequestDto villeLivraisonRequestDto) {
       log.info("Creating a new VilleLivraison");
       VilleLivraison villeLivraison = villeLivraisonMapper.toEntity(villeLivraisonRequestDto);
       log.info("VilleLivraisonRequestDto mapped to VilleLivraison : {}", villeLivraison);
        Optional<VilleLivraison> villeLivraisonOptional = villeLivraisonRepository
                .findByNomVilleIgnoreCaseOrReferenceIgnoreCase(villeLivraison.getNomVille(),villeLivraison.getReference());
        if (villeLivraisonOptional.isPresent()){
            log.warn("VilleLivraison already exist");
            throw new EntityExistsException("VilleLivraison already exist");
        }
        villeLivraison.setIsActive(true);
        VilleLivraison villeLivraison1 = villeLivraisonRepository.save(villeLivraison);
        Zone zone = villeLivraison1.getZone();
        villeLivraison1.setZone(zone);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonMapper.toDto1(villeLivraison1);
        log.info("{}" , villeLivraisonOptional);
        return villeLivraisonResponseDto;
    }


}