package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Zone}
 */
@Data
@NoArgsConstructor
public class ZoneRequestDto implements Serializable {
    @NotBlank(message = "nomZone is required")
    String nomZone;
}