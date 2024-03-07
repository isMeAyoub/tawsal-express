package com.simplon.parametre.dtos;

import com.simplon.parametre.model.entity.Zone;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
 */
@Value
public class VilleLivraisonDto implements Serializable {

    Long villeId;
    @NotBlank(message = "reference is Mandatory")
    String reference;
    @NotBlank(message = "nomVille is Mandatory")
    String nomVille;
    @NotNull(message = "isActive is Mandatory")
    Boolean isActive;
    @NotNull(message = "zoneDto is Mandatory")
    ZoneDto zoneDto;
}