package com.simplon.clients.parametre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ZoneClientResponseDto implements Serializable {

    @NotNull(message = "zoneId is required")
    Long zoneId;

    @NotBlank(message = "nomZone is required")
    String nomZone;
}