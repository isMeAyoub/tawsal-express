package com.simplon.demandes.service;

import com.simplon.demandes.dtos.request.ReclamationsRequestDto;
import com.simplon.demandes.dtos.response.ReclamationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReclamationsService {
    ReclamationsResponseDto createReclamation(ReclamationsRequestDto reclamationsRequestDto);

    Page<ReclamationsResponseDto> getAllReclamations(Pageable pageable);

    ReclamationsResponseDto getReclamationById(Long id);

    void makeReclamationAsRead(Long id);
    void makeReclamationAsTreated(Long id);
    void deleteReclamation(Long id);
}
