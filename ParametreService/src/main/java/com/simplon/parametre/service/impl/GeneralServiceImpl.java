package com.simplon.parametre.service.impl;

import com.simplon.clients.media.FileDataResponseDto;
import com.simplon.clients.media.MediaClient;
import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;
import com.simplon.parametre.mapper.GeneralMapper;
import com.simplon.parametre.model.entity.General;
import com.simplon.parametre.repository.GeneralRepository;
import com.simplon.parametre.service.GeneralService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
        try {
            FileDataResponseDto logoResponseDto = mediaClient.uploadImageToFIleSystem(logo).getBody();
            general.setLogo(logoResponseDto.getFilePath());
        } catch (IOException e) {
            throw new RuntimeException("Error while uploading image to the file system");
        }
        generalRepository.save(general);
        log.debug("Update logo {}", general);
    }

    /**
     * UpdateFavicon method is used to update the logo of the general information.
     * It takes a MultipartFile object as a parameter and returns a GeneralResponseDto object.
     *
     * @param logo the new favicon image
     *             (type MultipartFile)
     * @return void
     */
    @Override
    public void updateFavicon(MultipartFile favicon) {
        log.info("Update favicon");
        General general = generalRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("General instance not found"));
        try {
            FileDataResponseDto faviconResponseDto = mediaClient.uploadImageToFIleSystem(favicon).getBody();
            general.setFavicon(faviconResponseDto.getFilePath());
        } catch (IOException e) {
            throw new RuntimeException("Error while uploading image to the file system");
        }
        generalRepository.save(general);
        log.debug("Update favicon {}", general);
    }
}