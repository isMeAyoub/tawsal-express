package com.simplon.demandes.service.impl;

import com.simplon.clients.notification.EventType;
import com.simplon.clients.notification.Notification;
import com.simplon.clients.notification.NotificationClient;
import com.simplon.demandes.dtos.request.DemandeRamassageRequestDto;
import com.simplon.demandes.dtos.response.DemandeRamassageResponseDto;
import com.simplon.demandes.mapper.DemandeRamassageMapper;
import com.simplon.demandes.model.entity.DemandeRamassage;
import com.simplon.demandes.model.enums.RamassageEtat;
import com.simplon.demandes.repository.DemandeRamassageRepository;
import com.simplon.demandes.service.DemandeRamassageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DemandeRamassageServiceImpl implements DemandeRamassageService {

    private final DemandeRamassageRepository demandeRamassageRepository;
    private final DemandeRamassageMapper demandeRamassageMapper;
    private final NotificationClient notificationClient;

    @Override
    public DemandeRamassageResponseDto createDemandeRamassage(DemandeRamassageRequestDto demandeRamassageRequestDto) {
        log.debug("Creating demande ramassage {}", demandeRamassageRequestDto);
        DemandeRamassage demandeRamassage = demandeRamassageMapper.toEntity(demandeRamassageRequestDto);
        log.debug("Demande ramassage created {}", demandeRamassage);
        demandeRamassage.setRamassageEtat(RamassageEtat.NOUVELLE_RAMASSAGE);
        DemandeRamassage savedDemandeRamassage = demandeRamassageRepository.save(demandeRamassage);

        // Build notification message
        Notification notification = Notification.builder()
                .title("Nouvelle demande de ramassage")
                .message("Une nouvelle demande de ramassage a été créée")
                .eventType(EventType.NOUVELLE_DEMANDE_RAMASSAGE)
                .triggerBy(demandeRamassage.getCreatedBy())
                .build();

        // Send notification
        notificationClient.sendNotification(notification);
        log.debug("Notification sent {}", notification);
        return demandeRamassageMapper.toDto1(savedDemandeRamassage);
    }

    @Override
    public DemandeRamassageResponseDto getDemandeRamassageById(Long id) {
        log.debug("Getting demande ramassage by id {}", id);
        DemandeRamassage demandeRamassage = demandeRamassageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande ramassage not found with id " + id));
        log.debug("Demande ramassage found {}", demandeRamassage);
        return demandeRamassageMapper.toDto1(demandeRamassage);
    }

    @Override
    public Page<DemandeRamassageResponseDto> getAllDemandesRamassage(Pageable pageable) {
        log.debug("Getting all demandes ramassage");
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<DemandeRamassage> demandesRamassage = demandeRamassageRepository.findAll(sortedPage);
        log.debug("Demandes ramassage found {}", demandesRamassage);
        return demandesRamassage.map(demandeRamassageMapper::toDto1);
    }

    @Override
    public void deleteDemandeRamassageById(Long id) {
        log.debug("Deleting demande ramassage by id {}", id);
        DemandeRamassage demandeRamassage = demandeRamassageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande ramassage not found with id " + id));
        demandeRamassageRepository.delete(demandeRamassage);
        log.debug("Demande ramassage deleted {}", demandeRamassage);
    }

    @Override
    public void makeDemandeRamassageAsRead(Long id) {
        log.debug("Making demande ramassage as read by id {}", id);
        DemandeRamassage demandeRamassage = demandeRamassageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande ramassage not found with id " + id));
        if (demandeRamassage.getRamassageEtat() == RamassageEtat.NOUVELLE_RAMASSAGE) {
            demandeRamassage.setRamassageEtat(RamassageEtat.RAMASSAGE_RECUE);
        }
        demandeRamassageRepository.save(demandeRamassage);
        log.debug("Demande ramassage read {}", demandeRamassage);
    }

    @Override
    public void makeDemandeRamassageAsTreated(Long id) {
        log.debug("Making demande ramassage as treated by id {}", id);
        DemandeRamassage demandeRamassage = demandeRamassageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande ramassage not found with id " + id));
        if (demandeRamassage.getRamassageEtat() == RamassageEtat.RAMASSAGE_RECUE) {
            demandeRamassage.setRamassageEtat(RamassageEtat.RAMASSAGE_TRAITEE);
        }
        demandeRamassageRepository.save(demandeRamassage);
        log.debug("Demande ramassage treated {}", demandeRamassage);
    }

}
