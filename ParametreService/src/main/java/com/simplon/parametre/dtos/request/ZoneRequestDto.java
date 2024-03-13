package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Zone}
 */
@Getter
@Setter
@NoArgsConstructor
public class ZoneRequestDto implements Serializable {
    @NotBlank(message = "nomZone is required")
    String nomZone;
}