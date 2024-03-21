package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
 */
@Data
@NoArgsConstructor
public class VilleLivraisonRequestDto implements Serializable {
    @NotBlank(message = "reference is required")
    String reference;
    @NotBlank(message = "nomVille is required")
    String nomVille;
    @NotNull(message = "Zone is missing. Please select a zone")
    ZoneDto zone;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.Zone}
     */
    @Data
    @NoArgsConstructor
    public static class ZoneDto implements Serializable {
        @NotNull(message = "zoneId is required")
        Long zoneId;
    }
}