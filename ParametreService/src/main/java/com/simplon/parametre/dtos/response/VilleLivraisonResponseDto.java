package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
 */
@Value
public class VilleLivraisonResponseDto implements Serializable {
    @NotNull(message = "villeId is required")
    Long villeId;
    @NotBlank(message = "reference is required")
    String reference;
    @NotBlank(message = "nomVille is required")
    String nomVille;
    @NotNull(message = "isActive is required")
    Boolean isActive;
    @NotNull(message = "Zone is missing. Please select a zone")
    VilleLivraisonResponseDto.ZoneDto zone;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.Zone}
     */
    @Value
    public static class ZoneDto implements Serializable {
        @NotNull(message = "zoneId is required")
        Long zoneId;
        @NotBlank(message = "nomZone is required")
        String nomZone;
    }
}