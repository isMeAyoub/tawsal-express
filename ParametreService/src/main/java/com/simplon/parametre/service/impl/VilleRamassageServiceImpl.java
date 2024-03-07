package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import com.simplon.parametre.mapper.VilleRamassageMapper;
import com.simplon.parametre.model.entity.VilleRamassage;
import com.simplon.parametre.repository.VilleRamassageRepository;
import com.simplon.parametre.service.VilleRamassageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VilleRamassageServiceImpl implements VilleRamassageService {

    private final VilleRamassageRepository villeRamassageRepository;
    private final VilleRamassageMapper villeRamassageMapper;

    @Override
    public VilleRamassageResponseDto createVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto) {
        VilleRamassage villeRamassage = villeRamassageMapper.toEntity(villeRamassageRequestDto);
        Optional<VilleRamassage> villeRamassageOptional = villeRamassageRepository
                .findByNomVilleIgnoreCaseAndReferenceIgnoreCase(villeRamassage.getNomVille(),villeRamassage.getReference());
        if (villeRamassageOptional.isPresent()){
            throw new RuntimeException("VilleRamassage already exists");
        }
        VilleRamassage villeRamassage1 = villeRamassageOptional.get();
        VilleRamassage villeRamassageSaved = villeRamassageRepository.save(villeRamassage1);
        return villeRamassageMapper.toDto1(villeRamassageSaved);
    }

    @Override
    public Page<VilleRamassageResponseDto> getAllVilleRamassage(Pageable pageable) {
        return null;
    }
}
