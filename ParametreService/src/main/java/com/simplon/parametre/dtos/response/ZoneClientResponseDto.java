package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Zone}
 */
@Data
@NoArgsConstructor
public class ZoneClientResponseDto implements Serializable {

    @NotNull(message = "zoneId is required")
    Long zoneId;

    @NotBlank(message = "nomZone is required")
    String nomZone;
}