package com.simplon.demandes.service.impl;

import com.simplon.clients.notification.Notification;
import com.simplon.clients.notification.NotificationClient;
import com.simplon.demandes.dtos.request.ReclamationsRequestDto;
import com.simplon.demandes.dtos.response.ReclamationsResponseDto;
import com.simplon.demandes.mapper.ReclamationsMapper;
import com.simplon.demandes.model.entity.Reclamations;
import com.simplon.demandes.model.enums.ReclamationsEtat;
import com.simplon.demandes.repository.ReclamationsRepository;
import com.simplon.demandes.service.ReclamationsService;
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
public class ReclamationsServiceImpl implements ReclamationsService {
    private final ReclamationsRepository reclamationsRepository;
    private final ReclamationsMapper reclamationsMapper;
    private final NotificationClient notificationClient;

    public ReclamationsResponseDto createReclamation(ReclamationsRequestDto reclamationsRequestDto) {
        log.debug("Creating a new reclamation: {}", reclamationsRequestDto);
        Reclamations reclamations = reclamationsMapper.toEntity(reclamationsRequestDto);
        log.debug("Reclamation created: {}", reclamations);
        reclamations.setEtat(ReclamationsEtat.NOUVELLE_RECLAMATION);

        // TODO: i should get the user and set it to the reclamation
        reclamations = reclamationsRepository.save(reclamations);

        // Build the notification message
        Notification notification = Notification.builder()
                .title("Nouvelle réclamation")
                .message("Une nouvelle réclamation a été déposée")
                .eventType(com.simplon.clients.notification.EventType.NOUVELLE_RECLAMATION)
                .triggerBy(reclamations.getCreatedBy())
                .build();

        // Send a notification to the notification service
        notificationClient.sendNotification(notification);
        log.debug("Reclamation saved: {}", reclamations);
        return reclamationsMapper.toDto1(reclamations);
    }

    public Page<ReclamationsResponseDto> getAllReclamations(Pageable pageable) {
        log.debug("Getting all reclamations");
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        log.debug("Sorted page: {}", sortedPage);
        return reclamationsRepository.findAll(sortedPage)
                .map(reclamationsMapper::toDto1);
    }

    public ReclamationsResponseDto getReclamationById(Long id) {
        log.debug("Getting reclamation by id: {}", id);
        Reclamations reclamations = reclamationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));
        log.debug("Reclamation found: {}", reclamations);
        return reclamationsMapper.toDto1(reclamations);
    }

    public void makeReclamationAsRead(Long id) {
        log.debug("Making reclamation as read by id: {}", id);
        Reclamations reclamations = reclamationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));
        log.debug("Reclamation found: {}", reclamations);

        // Change the state of the reclamation to RECLAMATION_RECUE if it's still in NOUVELLE_RECLAMATION state
        if (reclamations.getEtat() == ReclamationsEtat.NOUVELLE_RECLAMATION) {
            reclamations.setEtat(ReclamationsEtat.RECLAMATION_RECUE);
        }
        reclamationsRepository.save(reclamations);
    }

    public void makeReclamationAsTreated(Long id) {
        log.debug("Making reclamation as treated by id: {}", id);
        Reclamations reclamations = reclamationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));
        log.debug("Reclamation found: {}", reclamations);

        // Change the state of the reclamation to RECLAMATION_TRAITEE if it's still in RECLAMATION_RECUE state
        if (reclamations.getEtat() == ReclamationsEtat.RECLAMATION_RECUE) {
            reclamations.setEtat(ReclamationsEtat.RECLAMATION_TRAITEE);
        }
        reclamationsRepository.save(reclamations);
    }

    public void deleteReclamation(Long id) {
        log.debug("Deleting reclamation by id: {}", id);
        Reclamations reclamations = reclamationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id " + id));
        log.debug("Reclamation found: {}", reclamations);
        reclamationsRepository.delete(reclamations);
    }

}
