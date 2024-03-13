package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Zone}
 */
@Getter
@Setter
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

    /**
     * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class VilleLivraisonDto implements Serializable {
        @NotNull(message = "villeId is required")
        Long villeId;
        @NotBlank(message = "nomVille is required")
        String nomVille;
    }
}