package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.VilleRamassage}
 */
@Value
@AllArgsConstructor
public class VilleRamassageResponseDto implements Serializable {
    Long villeId;
    @NotBlank(message = "reference is required")
    String reference;
    @NotBlank(message = "nomVille is required")
    String nomVille;
    @NotNull(message = "isActive is required")
    Boolean isActive;
}