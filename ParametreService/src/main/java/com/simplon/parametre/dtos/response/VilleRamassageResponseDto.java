package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleRamassage}
 */
@Value
public class VilleRamassageResponseDto implements Serializable {
    Long villeId;
    @NotBlank
    String reference;
    @NotBlank
    String nomVille;
    @NotNull
    Boolean isActive;
}