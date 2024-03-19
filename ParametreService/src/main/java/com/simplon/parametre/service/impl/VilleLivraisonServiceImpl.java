package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.VilleLivraisonRequestDto;
import com.simplon.parametre.dtos.response.VilleLivraisonResponseDto;
import com.simplon.parametre.mapper.VilleLivraisonMapper;
import com.simplon.parametre.model.entity.VilleLivraison;
import com.simplon.parametre.model.entity.Zone;
import com.simplon.parametre.repository.VilleLivraisonRepository;
import com.simplon.parametre.repository.ZoneRepository;
import com.simplon.parametre.service.VilleLivraisonService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This Class implements the {@link VilleLivraisonService} interface.
 * It provides concrete implementations for the CRUD operations (Create, Read, Update, Delete)
 * and other functionalities related to VilleLivraison management.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VilleLivraisonServiceImpl implements VilleLivraisonService {

    private final VilleLivraisonRepository villeLivraisonRepository;
    private final VilleLivraisonMapper villeLivraisonMapper;
    private final ZoneRepository zoneRepository;

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
        Zone zone = zoneRepository.findById(villeLivraisonRequestDto.getZone().getZoneId()).orElseThrow(
                () -> new EntityNotFoundException("Zone not found with id : " + villeLivraisonRequestDto.getZone().getZoneId())
        );
        villeLivraison.setZone(zoneRepository.findById(villeLivraisonRequestDto.getZone().getZoneId()).orElseThrow());
        VilleLivraison villeLivraison1 = villeLivraisonRepository.save(villeLivraison);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonMapper.toDto1(villeLivraison1);
        log.info("{}" , villeLivraisonOptional);
        return villeLivraisonResponseDto;
    }

    @Override
    public VilleLivraisonResponseDto getByIdVilleLivraison(Long id) {
        log.info("Getting VilleLivraison by id : {}", id);
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("VilleLivraison not found with id : " + id)
        );
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonMapper.toDto1(villeLivraison);
        return villeLivraisonResponseDto;
    }


    // TODO: i should fix this method
    @Override
    public VilleLivraisonResponseDto updateVilleLivraison(Long id, VilleLivraisonRequestDto villeLivraisonRequestDto) {
        log.info("Updating VilleLivraison with id : {}", id);
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("VilleLivraison not found with id : " + id)
        );
        VilleLivraison villeLivraison1 = villeLivraisonRepository.findByNomVilleIgnoreCaseOrReferenceIgnoreCaseAndVilleIdNot(villeLivraisonRequestDto.getNomVille(), villeLivraisonRequestDto.getReference(), id).orElseThrow(
                () -> new EntityExistsException("VilleLivraison already exist")
        );
        villeLivraison.setNomVille(villeLivraisonRequestDto.getNomVille());
        villeLivraison.setReference(villeLivraisonRequestDto.getReference());
        villeLivraison.setZone(zoneRepository.findById(villeLivraisonRequestDto.getZone().getZoneId()).orElseThrow());
        villeLivraison1 = villeLivraisonRepository.save(villeLivraison);
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonMapper.toDto1(villeLivraison1);
        log.info("VilleLivraison updated successfully");
        return villeLivraisonResponseDto;
    }
    @Override
    public void deleteVilleLivraison(Long id) {
        log.info("Deleting VilleLivraison by id : {}", id);
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("VilleLivraison not found with id : " + id)
        );
        villeLivraisonRepository.delete(villeLivraison);
        log.info("VilleLivraison deleted successfully");
    }

    @Override
    public Page<VilleLivraisonResponseDto> getAllVilleLivraison(Pageable pageable , String search) {
        log.info("Getting all VilleLivraison");
        Page<VilleLivraison> villeLivraisonPage = villeLivraisonRepository.getALlVilleLivraisonAndSearch(search, pageable);
        Page<VilleLivraisonResponseDto> villeLivraisonResponseDtoPage = villeLivraisonPage.map(villeLivraisonMapper::toDto1);
        log.info("All VilleLivraison returned successfully");
        return villeLivraisonResponseDtoPage;
    }

    @Override
    public void changeStatusOfVilleLivraison(Long id) {
        log.info("Changing status of VilleLivraison with id : {}", id);
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("VilleLivraison not found with id : " + id)
        );
        villeLivraison.setIsActive(!villeLivraison.getIsActive());
        villeLivraisonRepository.save(villeLivraison);
        log.info("Status of VilleLivraison changed successfully");
    }


}