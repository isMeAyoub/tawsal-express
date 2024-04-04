package com.simplon.clients.parametre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
public class ZoneResponseDto implements Serializable {
    @NotNull(message = "zoneId is required")
    Long zoneId;
    @NotBlank(message = "nomZone is required")
    String nomZone;
    @NotNull(message = "isActive is required")
    Boolean isActive;
    @NotNull(message = "villeLivraison is required")
    List<VilleLivraisonDto> villesLivraison;

    @Data
    @NoArgsConstructor
    public static class VilleLivraisonDto implements Serializable {
        @NotNull(message = "villeId is required")
        Long villeId;
        @NotBlank(message = "nomVille is required")
        String nomVille;
    }
}