package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
 */
@Getter
@Setter
@NoArgsConstructor
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
    ZoneDto zone;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.Zone}
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ZoneDto implements Serializable {
        @NotNull(message = "zoneId is required")
        Long zoneId;
        @NotBlank(message = "nomZone is required")
        String nomZone;
    }
}