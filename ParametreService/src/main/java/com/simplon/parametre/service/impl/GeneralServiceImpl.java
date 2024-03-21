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
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * The GeneralServiceImpl class implements the GeneralService interface and provides methods to manage general information.
 * It's a service class used in Spring Boot applications to provide general information management services.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final GeneralRepository generalRepository;
    private final GeneralMapper generalMapper;

    @PostConstruct
    public void init() {
        log.info("Init the database");
        if (generalRepository.count() == 0) {
            General general = General.getInstance();
            general.initialize();
            generalRepository.save(general);
            log.info("Database initialized");
        } else {
            log.info("General instance already exists in the database");
        }
    }

    @Override
    public GeneralResponseDto getGeneralInstance() {
        log.info("Get general information");
        General general = generalRepository.findAll().stream().findFirst().orElseThrow(
                () -> new RuntimeException("General instance not found")
        );
        if (generalRepository.count() != 1) {
            throw new RuntimeException("There should be exactly one General instance in the database");
        }
        log.debug("General information {}", general);
        GeneralResponseDto generalResponseDto = generalMapper.toDto1(general);
        log.debug("General information {}", generalResponseDto);
        return generalResponseDto;
    }

    @Override
    public GeneralResponseDto updateGeneral(GeneralRequestDto generalRequestDto) {
        log.info("Update general information");
        General general = General.getInstance();
        log.debug("General information {}", general);
        generalMapper.partialUpdate(generalRequestDto, general);
        generalRepository.save(general);
        log.debug("Update general information {}", general);
        return generalMapper.toDto1(general);
    }
}