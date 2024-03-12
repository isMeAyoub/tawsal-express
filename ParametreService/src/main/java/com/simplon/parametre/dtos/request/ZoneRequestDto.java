package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Zone}
 */
@Value
public class ZoneRequestDto implements Serializable {
    @NotBlank(message = "nomZone is required")
    String nomZone;
}