package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
 */
@Value
public class VilleLivraisonRequestDto implements Serializable {
    @NotBlank(message = "reference is required")
    String reference;
    @NotBlank(message = "nomVille is required")
    String nomVille;
    @NotNull(message = "Zone is missing. Please select a zone")
    VilleLivraisonRequestDto.ZoneDto zone;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.Zone}
     */
    @Value
    public static class ZoneDto implements Serializable {
        @NotNull(message = "zoneId is required")
        Long zoneId;
    }
}