package com.simplon.parametre.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Zone}
 */
@Value
public class ZoneDto implements Serializable {
    Long zoneId;
    @NotBlank(message = "nomZone is Mandatory")
    String nomZone;
    @NotNull(message = "isActive is Mandatory")
    Boolean isActive;
    List<VilleLivraisonDto1> villesLivraison;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
     */
    @Value
    public static class VilleLivraisonDto1 implements Serializable {
        Long villeId;
        String reference;
        String nomVille;
        Boolean isActive;
    }
}