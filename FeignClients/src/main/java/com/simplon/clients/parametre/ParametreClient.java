package com.simplon.clients.parametre;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PARAMETRE")
public interface ParametreClient {

    @GetMapping(value = "/api/v1/parametre/ville-ramassages/{villeId}" , produces = "application/json")
    ResponseEntity<VilleRamassageResponseDto> getVilleRamassageById(@NotNull @PathVariable("villeId") Long villeId);

    @GetMapping(value = "/api/v1/parametre/zones/{zoneId}" , produces = "application/json")
    ResponseEntity<ZoneResponseDto> getZone(@NotNull @PathVariable("zoneId") Long zoneId);

    @GetMapping("/api/v1/parametre/zones/client/{zoneId}")
    ResponseEntity<ZoneClientResponseDto> getZoneToClient(@NotNull @PathVariable("zoneId") Long zoneId);
}
