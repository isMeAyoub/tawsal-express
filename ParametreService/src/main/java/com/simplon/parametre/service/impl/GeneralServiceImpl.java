package com.simplon.parametre.service.impl;

import com.simplon.clients.media.FileResponseDto;
import com.simplon.clients.media.MediaClient;
import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;
import com.simplon.parametre.mapper.GeneralMapper;
import com.simplon.parametre.model.entity.General;
import com.simplon.parametre.repository.GeneralRepository;
import com.simplon.parametre.service.GeneralService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The GeneralServiceImpl class implements the GeneralService interface and provides methods to manage general information.
 * It's a service class used in Spring Boot applications to provide general information management services.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Service
@AllArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final GeneralRepository generalRepository;
    private final GeneralMapper generalMapper;
    private final MediaClient mediaClient;

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

    /**
     * GetGeneralInstance method is used to get the general information.
     * It returns a GeneralResponseDto object.
     *
     * @return GeneralResponseDto
     */
    @Override
    public GeneralResponseDto getGeneralInstance() {
        log.info("Get general information");
        General general = generalRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("General instance not found"));
        if (generalRepository.count() != 1) {
            throw new RuntimeException("There should be exactly one General instance in the database");
        }
        log.debug("General information {}", general);
        GeneralResponseDto generalResponseDto = generalMapper.toDto1(general);
        log.debug("General information {}", generalResponseDto);
        return generalResponseDto;
    }

    /**
     * UpdateGeneral method is used to update the general information.
     * It takes a GeneralRequestDto object as a parameter and returns a GeneralResponseDto object.
     *
     * @param generalRequestDto the new general information
     *                          (type GeneralRequestDto)
     * @return GeneralResponseDto
     */
    @Override
    public GeneralResponseDto updateGeneral(GeneralRequestDto generalRequestDto) {
        log.info("Update general information");

        // Fetch the existing General instance from the database
        General general = generalRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("General instance not found"));

        generalMapper.partialUpdate(generalRequestDto, general);

        // Save the updated General instance
        generalRepository.save(general);

        log.debug("Update general information {}", general);
        return generalMapper.toDto1(general);
    }

    /**
     * UpdateLogo method is used to update the logo of the general information.
     * It takes a MultipartFile object as a parameter and returns a GeneralResponseDto object.
     *
     * @param logo the new logo image
     *             (type MultipartFile)
     * @return void
     */
    @Override
    public void updateLogo(MultipartFile logo) {
        log.info("Update logo");
        General general = generalRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("General instance not found"));
        FileResponseDto logoResponseDto = mediaClient.uploadImageToFirebase(logo).getBody();
        general.setLogo(logoResponseDto.getFilePath());
        generalRepository.save(general);
        log.debug("Update logo {}", general);
    }

    /**
     * UpdateFavicon method is used to update the logo of the general information.
     * It takes a MultipartFile object as a parameter and returns a GeneralResponseDto object.
     *
     * @param favicon the new favicon image
     *                (type MultipartFile)
     * @return void
     */
    @Override
    public void updateFavicon(MultipartFile favicon) {
        log.info("Update favicon");
        General general = generalRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("General instance not found"));
        FileResponseDto faviconResponseDto = mediaClient.uploadImageToFirebase(favicon).getBody();
        general.setFavicon(faviconResponseDto.getFilePath());
        generalRepository.save(general);
        log.debug("Update favicon {}", general);
    }
}