package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;
import com.simplon.parametre.service.GeneralService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This class is used to manage the general information of the application
 *
 * @Author Ayoub Ait Si Ahmad
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parametre/general")
public class GeneralController {

    private final GeneralService generalService;

    /**
     * This endponit is used to get the general information of the application
     * We Use Singleton pattern to get the general information
     *
     * @return GeneralResponseDto
     * @Author Ayoub Ait Si Ahmad
     */
    @GetMapping
    public ResponseEntity<GeneralResponseDto> getGeneral() {
        log.info("Get general information");
        return ResponseEntity.ok(generalService.getGeneralInstance());
    }

    /**
     * This endpoint is used to update the general information of the application
     *
     * @param generalRequestDto
     * @return GeneralResponseDto
     * @Author Ayoub Ait Si Ahmad
     */
    @PutMapping
    public ResponseEntity<GeneralResponseDto> updateGeneral(@Valid @RequestBody GeneralRequestDto generalRequestDto) {
        log.info("Update general information");
        return ResponseEntity.ok(generalService.updateGeneral(generalRequestDto));
    }

    /**
     * This endpoint is used to update the logo of the application
     *
     * @param logo
     * @return void
     * @Author Ayoub Ait Si Ahmad
     */
    @PatchMapping(value = "/logo")
    public ResponseEntity<Void> updateLogo(@RequestParam("logo") MultipartFile logo) {
        log.info("Update logo");
        generalService.updateLogo(logo);
        return ResponseEntity.ok().build();
    }

    /**
     * This endpoint is used to update the favicon of the application
     *
     * @param favicon
     * @return void
     * @Author Ayoub Ait Si Ahmad
     */
    @PatchMapping(value = "/favicon")
    public ResponseEntity<Void> updateFavicon(@RequestParam("favicon") MultipartFile favicon) {
        log.info("Update favicon");
        generalService.updateFavicon(favicon);
        return ResponseEntity.ok().build();
    }

}
