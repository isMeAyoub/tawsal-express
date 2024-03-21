package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;
import com.simplon.parametre.service.GeneralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parametre/general")
public class GeneralController {

    private final GeneralService generalService;

    @GetMapping
    public ResponseEntity<GeneralResponseDto> getGeneral() {
        log.info("Get general information");
        return ResponseEntity.ok(generalService.getGeneralInstance());
    }

    @PutMapping
    public ResponseEntity<GeneralResponseDto> updateGeneral(
            @RequestBody @Valid GeneralRequestDto generalRequestDto
    ) {
        log.info("Update general information");
        return ResponseEntity.ok(generalService.updateGeneral(generalRequestDto));
    }
}
