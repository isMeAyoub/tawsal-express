package com.simplon.parametre.service.impl;

import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;
import com.simplon.parametre.mapper.GeneralMapper;
import com.simplon.parametre.model.entity.General;
import com.simplon.parametre.repository.GeneralRepository;
import com.simplon.parametre.service.GeneralService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final GeneralRepository generalRepository;
    private final GeneralMapper generalMapper;

    @PostConstruct
    public void init() {
        log.info("Init the database");
        General general = General.getInstance();
        general.initialize();
        generalRepository.save(general);
        log.info("Database initialized");
    }

    @Override
    public GeneralResponseDto getGeneralInstance() {
        General general = General.getInstance();
        return generalMapper.toDto1(general);
    }

    @Override
    public GeneralResponseDto updateGeneral(GeneralRequestDto generalRequestDto) {
        General general = General.getInstance();
        generalMapper.partialUpdate(generalRequestDto, general);
        generalRepository.save(general);
        log.info("Update general information {}", general);
        return generalMapper.toDto1(general);
    }
}