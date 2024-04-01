package com.simplon.clients.parametre;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PARAMETRE")
public interface ParametreClient {

//    @GetMapping("/api/v1/parametre/general/{villeId}")
//    ResponseEntity<VilleRamassageResponseDto> getVilleRamassageById(@NotNull @PathVariable("villeId") Long villeId);
}
