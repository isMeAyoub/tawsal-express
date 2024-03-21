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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        log.info("Attempting to create a new VilleLivraison : {}", villeLivraisonRequestDto);
        VilleLivraison villeLivraison = villeLivraisonMapper.toEntity(villeLivraisonRequestDto);
        log.info("VilleLivraisonRequestDto mapped to VilleLivraison : {}", villeLivraison);
        Optional<VilleLivraison> villeLivraisonOptional = villeLivraisonRepository
                .findByNomVilleIgnoreCaseOrReferenceIgnoreCase(villeLivraison.getNomVille(), villeLivraison.getReference());
        if (villeLivraisonOptional.isPresent()) {
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
        log.info("VilleLivraison created successfully : {}", villeLivraison1);
        return villeLivraisonMapper.toDto1(villeLivraison1);
    }

    @Override
    public VilleLivraisonResponseDto getByIdVilleLivraison(Long id) {
        log.info("Attempting to get VilleLivraison by id : {}", id);
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("VilleLivraison not found with id : " + id)
        );
        VilleLivraisonResponseDto villeLivraisonResponseDto = villeLivraisonMapper.toDto1(villeLivraison);
        log.info("VilleLivraison retrieved successfully : {}", villeLivraisonResponseDto);
        return villeLivraisonResponseDto;
    }

    @Override
    public VilleLivraisonResponseDto updateVilleLivraison(Long id, VilleLivraisonRequestDto villeLivraisonRequestDto) {
        log.info("Attempting to update VilleLivraison with id : {}", id);
        VilleLivraison villeLivraison = villeLivraisonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VilleLivraison not found : " + id));
        Optional<VilleLivraison> villeLivraisonOptional = villeLivraisonRepository
                .findByNomVilleIgnoreCaseAndVilleIdNotOrReferenceIgnoreCaseAndVilleIdNot(villeLivraisonRequestDto.getNomVille(), id, villeLivraisonRequestDto.getReference(), id);
        if (villeLivraisonOptional.isPresent()) {
            log.warn("VilleLivraison already exist");
            throw new EntityExistsException(" VilleLivraison with the same nomVille or reference already exist");
        }
        Zone zone = zoneRepository.findById(villeLivraisonRequestDto.getZone().getZoneId()).orElseThrow(
                () -> new EntityNotFoundException("Zone not found with id : " + villeLivraisonRequestDto.getZone().getZoneId())
        );
        villeLivraison = villeLivraisonMapper.partialUpdate(villeLivraisonRequestDto, villeLivraison);
        villeLivraison = villeLivraisonRepository.save(villeLivraison);
        log.info("VilleLivraison updated successfully : {}", villeLivraison);
        return villeLivraisonMapper.toDto1(villeLivraison);
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
    public Page<VilleLivraisonResponseDto> getAllVilleLivraison(Pageable pageable, String search) {
        log.info("Getting all VilleLivraison");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<VilleLivraison> villeLivraisonPage = villeLivraisonRepository.getALlVilleLivraisonAndSearch(search, sortedPageable);
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

    // TODO : if i have time i will implement this method
    // Delete a VilleLivraison from a Zone and Add VilleLivraison to a Zone
}