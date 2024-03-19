package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.VilleRamassageRequestDto;
import com.simplon.parametre.dtos.response.VilleRamassageResponseDto;
import com.simplon.parametre.mapper.VilleRamassageMapper;
import com.simplon.parametre.model.entity.VilleRamassage;
import com.simplon.parametre.repository.VilleRamassageRepository;
import com.simplon.parametre.service.VilleRamassageService;
import io.github.wimdeblauwe.errorhandlingspringbootstarter.ResponseErrorProperty;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

/**
 * This class implements the {@link VilleRamassageService} interface.
 * It provides concrete implementations for the CRUD operations (Create, Read, Update, Delete)
 * and other functionalities related to VilleRamassage management.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VilleRamassageServiceImpl implements VilleRamassageService {

    private final VilleRamassageRepository villeRamassageRepository;
    private final VilleRamassageMapper villeRamassageMapper;

    @Override
    public VilleRamassageResponseDto createVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto) {
        log.info("Request received to create VilleRamassage: {}", villeRamassageRequestDto);
        VilleRamassage villeRamassage = villeRamassageMapper.toEntity(villeRamassageRequestDto);
        log.debug("Converted request DTO to entity: {}", villeRamassage);

        // Check for existing VilleRamassage with case-insensitive name/reference
        log.debug("Checking for existing VilleRamassage with name: {} or reference: {}", villeRamassage.getNomVille(),
                villeRamassage.getReference());
        Optional<VilleRamassage> existingVilleRamassage = villeRamassageRepository
                .findByNomVilleIgnoreCaseOrReferenceIgnoreCase(villeRamassage.getNomVille(),
                        villeRamassage.getReference());

        if (existingVilleRamassage.isPresent()) {
            log.error("VilleRamassage with name: {} or reference: {} already exists", villeRamassage.getNomVille(), villeRamassage.getReference());
            throw new EntityExistsException("VilleRamassage with name: " + villeRamassage.getNomVille() + " or reference: " + villeRamassage.getReference() + " already exists");
        }

        villeRamassage.setIsActive(true);
        log.debug("Set VilleRamassage as active");
        VilleRamassage savedVilleRamassage = villeRamassageRepository.save(villeRamassage);

        log.info("Saved VilleRamassage to repository: {}", savedVilleRamassage);
        return villeRamassageMapper.toDto1(savedVilleRamassage);
    }

    @Override
    public Page<VilleRamassageResponseDto> getAllVilleRamassage(String search, Pageable pageable) {
        log.info("Request received to get all VilleRamassage (search: {}, pageable: {})", search, pageable);
        Page<VilleRamassage> villeRamassagePage = villeRamassageRepository.searchVilleRamassage(search, pageable);

        log.debug("Retrieved VilleRamassage page from repository: {}", villeRamassagePage);
        return villeRamassagePage.map(villeRamassageMapper::toDto1);
    }

    @Override
    public VilleRamassageResponseDto getVilleRamassageById(Long villeId) {
        log.info("Request received to get VilleRamassage with ID: {}", villeId);
        VilleRamassage villeRamassageOptional = villeRamassageRepository.findById(villeId).orElseThrow(
                () -> new EntityNotFoundException("VilleRamassage with ID " + villeId + " not found"));
        log.debug("Retrieved VilleRamassage from repository: {}", villeRamassageOptional);
        return villeRamassageMapper.toDto1(villeRamassageOptional);
    }

    @Override
    public VilleRamassageResponseDto updateVilleRamassage(VilleRamassageRequestDto villeRamassageRequestDto,
                                                          Long villeId) {
        log.info("Request received to update VilleRamassage with ID: {}", villeId);
        Optional<VilleRamassage> existingVilleRamassageOptional = villeRamassageRepository.findById(villeId);
        if (!existingVilleRamassageOptional.isPresent()) {
            log.error("VilleRamassage with ID {} not found", villeId);
            throw new EntityNotFoundException("VilleRamassage with ID " + villeId + " not found");
        }
        if (checkIfNameAlreadyExists(villeRamassageRequestDto.getNomVille(), villeRamassageRequestDto.getReference(), villeId)) {
            log.error("VilleRamassage with duplicate nomVille or reference exists");
            throw new EntityExistsException("VilleRamassage with name: " + villeRamassageRequestDto.getNomVille() + " or reference: " + villeRamassageRequestDto.getReference() + " already exists");
        }

        VilleRamassage existingVilleRamassage = existingVilleRamassageOptional.get();
        villeRamassageMapper.partialUpdate(villeRamassageRequestDto, existingVilleRamassage);

        VilleRamassage savedVilleRamassage = villeRamassageRepository.save(existingVilleRamassage);
        log.info("VilleRamassage with ID {} updated successfully", villeId);

        return villeRamassageMapper.toDto1(savedVilleRamassage);
    }

    public boolean checkIfNameAlreadyExists(String nomVille, String reference, Long excludeId) {

        // Check for potential conflicts, excluding the entity with the specified ID
        Optional<VilleRamassage> conflictingVilleRamassage = villeRamassageRepository.findByNomVilleIgnoreCaseOrReferenceIgnoreCase(nomVille, reference)
                .filter(v -> !v.getVilleId().equals(excludeId));

        return conflictingVilleRamassage.isPresent();
    }

    @Override
    public void changeStatusOfVilleRamassage(Long villeId) {
        log.info("Request received to change status of VilleRamassage with ID: {}", villeId);
        Optional<VilleRamassage> villeRamassageOptional = villeRamassageRepository.findById(villeId);

        if (villeRamassageOptional.isPresent()) {
            VilleRamassage villeRamassage = villeRamassageOptional.get();
            villeRamassage.setIsActive(!villeRamassage.getIsActive());
            villeRamassageRepository.save(villeRamassage);
            log.info("Status of VilleRamassage with ID {} changed to {}", villeId, villeRamassage.getIsActive());
        } else {
            log.error("VilleRamassage with ID {} not found", villeId);
            throw new EntityNotFoundException("VilleRamassage with ID " + villeId + " not found");
        }
    }

    @Override
    public void deleteVilleRamassageById(Long villeId) {
        log.info("Request to delete VilleRamassage in service");
        Optional<VilleRamassage> villeRamassageOptional = villeRamassageRepository.findById(villeId);
        if (!villeRamassageOptional.isPresent()) {
            log.error("VilleRamassage don't exists");
            throw new EntityNotFoundException("VilleRamassage with ID " + villeId + " not found");
        }
        villeRamassageRepository.delete(villeRamassageOptional.get());
        log.debug("VilleRamassage has deleted: {}", villeId);
    }
}
