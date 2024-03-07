package com.simplon.parametre.controller;

import com.simplon.parametre.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/zones")
public class ZoneController {

    private final ZoneService zoneService;
    private final Integer SIZE = 10;
    private final Integer PAGE = 0;
/*
    @GetMapping
    ResponseEntity<Page<ZoneResponseDto>> getAllZone(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    ) {
        int pageSize = (size != null) ? size : SIZE;
        int pageNumber = (page != null) ? page : PAGE;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ZoneResponseDto> zoneResponseDtoPage = zoneService.getAllZone(pageable);
        return new ResponseEntity<Page<ZoneResponseDto>>(zoneResponseDtoPage, HttpStatus.ACCEPTED);
    }


    @PostMapping
    ResponseEntity<ZoneResponseDto> createZone(@RequestBody @Valid ZoneRequestDto zoneRequestDto){
        ZoneResponseDto zoneResponseDto = zoneService.createZone(zoneRequestDto);
        return zoneResponseDto;
    }

     */
}