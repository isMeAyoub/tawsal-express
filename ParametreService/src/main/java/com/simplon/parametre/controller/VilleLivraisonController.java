package com.simplon.parametre.controller;

import com.simplon.parametre.dtos.VilleLivraisonDto;
import com.simplon.parametre.service.VilleLivraisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/api/villes")
public class VilleLivraisonController {

    private final VilleLivraisonService villeLivraisonService;

    @GetMapping
    public Page<VilleLivraisonDto> getAllVilleLivraison(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page){
        Pageable paging = PageRequest.of(page, size);
        Page<VilleLivraisonDto> villeLivraisonDtos = villeLivraisonService.getAllVilleLivraison(paging);
        return villeLivraisonDtos;
    }
}