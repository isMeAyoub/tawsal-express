package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.TarifRequestDto;
import com.simplon.parametre.dtos.response.TarifResponseDto;
import com.simplon.parametre.mapper.TarifMapper;
import com.simplon.parametre.model.entity.Tarif;
import com.simplon.parametre.model.entity.VilleLivraison;
import com.simplon.parametre.model.entity.VilleRamassage;
import com.simplon.parametre.repository.TarifRepository;
import com.simplon.parametre.repository.VilleLivraisonRepository;
import com.simplon.parametre.repository.VilleRamassageRepository;
import com.simplon.parametre.service.TarifService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This Class is Implements the {@link TarifService} Interface
 * All the methods in the interface are implemented here.
 * This class is used to implement business logic on the entity {@link Tarif}
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TarifServiceImpl implements TarifService {

    private final TarifMapper tarifMapper;
    private final TarifRepository tarifRepository;
    private final VilleLivraisonRepository villeLivraisonRepository;
    private final VilleRamassageRepository villeRamassageRepository;

    @Override
    public TarifResponseDto createTarif(TarifRequestDto tarifRequestDto) {
        log.info("Attempting to create a new Tarif : {}", tarifRequestDto);
        Tarif tarif = tarifMapper.toEntity(tarifRequestDto);
        VilleRamassage villeRamassage = villeRamassageRepository.findById(tarifRequestDto.getVilleRamassage().getVilleId())
                .orElseThrow(() -> new EntityNotFoundException("VilleRamassage not found"));
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(tarifRequestDto.getVilleLivraison().getVilleId())
                .orElseThrow(() -> new EntityNotFoundException("VilleLivraison not found"));
        tarif.setVilleRamassage(villeRamassage);
        tarif.setVilleLivraison(villeLivraison);
        Optional<Tarif> tarifOptional = tarifRepository.findByVilleRamassageNomVilleAndVilleLivraisonNomVille(villeRamassage.getNomVille(), villeLivraison.getNomVille());
        if (tarifOptional.isPresent()) {
            log.error("Tarif already exists");
            throw new EntityExistsException("Tarif with VilleRamassage " + villeRamassage.getNomVille() + " and VilleLivraison " + villeLivraison.getNomVille() + " already exists");
        }
        tarif = tarifRepository.save(tarif);
        log.info("Tarif created successfully : {}", tarif);
        return tarifMapper.toDto1(tarif);
    }

    @Override
    public TarifResponseDto updateTarif(Long id, TarifRequestDto tarifRequestDto) {
        log.info("Attempting to update Tarif with id : {}", id);
        Tarif tarif = tarifRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarif not found : " + id));
        VilleRamassage villeRamassage = villeRamassageRepository.findById(tarifRequestDto.getVilleRamassage().getVilleId())
                .orElseThrow(() -> new EntityNotFoundException("VilleRamassage not found"));
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(tarifRequestDto.getVilleLivraison().getVilleId())
                .orElseThrow(() -> new EntityNotFoundException("VilleLivraison not found"));

        Optional<Tarif> tarifOptional = tarifRepository.findByVilleRamassageNomVilleAndVilleLivraisonNomVille(villeRamassage.getNomVille(), villeLivraison.getNomVille());
        if (tarifOptional.isPresent() && !tarifOptional.get().getTarifId().equals(id)) {
            log.error("Tarif already exists");
            throw new EntityExistsException("Tarif with VilleRamassage " + villeRamassage.getNomVille() + " and VilleLivraison " + villeLivraison.getNomVille() + " already exists");
        }
        tarif = tarifMapper.partialUpdate(tarifRequestDto, tarif);
        tarif = tarifRepository.save(tarif);
        log.info("Tarif updated successfully : {}", tarif);
        return tarifMapper.toDto1(tarif);
    }

    @Override
    public Page<TarifResponseDto> getAllTarifs(Pageable pageable, String search, String VilleRamassage, String VilleLivraison) {
        log.info("Getting all Tarifs");
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<Tarif> tarifs = tarifRepository.findAllTarifs(search, VilleRamassage, VilleLivraison, sortedPage);
        log.info("Tarifs retrieved successfully");
        return tarifs.map(tarifMapper::toDto1);
    }

    @Override
    public TarifResponseDto getTarifById(Long id) {
        log.info("Getting Tarif with id : {}", id);
        Tarif tarif = tarifRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarif not found : " + id));
        log.info("Tarif retrieved successfully : {}", tarif);
        return tarifMapper.toDto1(tarif);
    }

    @Override
    public void deleteTarif(Long id) {
        log.info("Attempting to delete Tarif with id : {}", id);
        Tarif tarif = tarifRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarif not found : " + id));
        tarifRepository.delete(tarif);
        log.info("Tarif deleted successfully : {}", tarif);
    }
}