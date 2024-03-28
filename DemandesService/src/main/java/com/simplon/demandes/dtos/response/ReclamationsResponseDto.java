package com.simplon.demandes.dtos.response;

import com.simplon.demandes.model.enums.ReclamationsEtat;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.simplon.demandes.model.entity.Reclamations}
 */
@Value
public class ReclamationsResponseDto implements Serializable {
    @NotNull(message = "Created Date cannot be null")
    LocalDateTime createdDate;
    Long reclamationId;
    String objet;
    String message;
    ReclamationsEtat etat;
}