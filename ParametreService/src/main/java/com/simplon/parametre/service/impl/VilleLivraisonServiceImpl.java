package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.VilleLivraisonDto;
import com.simplon.parametre.mapper.VilleLivraisonMapper;
import com.simplon.parametre.model.entity.VilleLivraison;
import com.simplon.parametre.repository.VilleLivraisonRepository;
import com.simplon.parametre.service.VilleLivraisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VilleLivraisonServiceImpl implements VilleLivraisonService {

    private final VilleLivraisonRepository villeLivraisonRepository;
    private final VilleLivraisonMapper villeLivraisonMapper;

    @Override
    public VilleLivraisonDto getVilleLivraisonById(Long villeId) {
        Optional<VilleLivraison> villeLivraisonOptional = villeLivraisonRepository.findById(villeId);
        if (villeLivraisonOptional.isPresent()) {
            return villeLivraisonMapper.toDto(villeLivraisonOptional.get());
        } else {
            // TODO: Handle the case where the VilleLivraison is not found
            return null;
        }
    }

    // TODO: Shold i pass the search
    @Override
    public Page<VilleLivraisonDto> getAllVilleLivraison(Pageable pageable) {
        Page<VilleLivraison> villeLivraisons = villeLivraisonRepository.findAll(pageable);
        return villeLivraisons.map(villeLivraisonMapper::toDto);
    }
    @Override
    public VilleLivraisonDto createVilleLivraison(VilleLivraisonDto villeLivraisonDto) {
        return null;
    }
}
