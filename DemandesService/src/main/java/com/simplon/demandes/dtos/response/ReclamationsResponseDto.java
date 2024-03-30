package com.simplon.demandes.dtos.response;

import com.simplon.demandes.model.enums.ReclamationsEtat;
import com.simplon.demandes.model.enums.ReclamationsType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.simplon.demandes.model.entity.Reclamations}
 */
@Data
@NoArgsConstructor
public class ReclamationsResponseDto implements Serializable {

    @NotNull(message = "Updated Date cannot be null")
    Long reclamationId;

    @NotBlank(message = "Objet cannot be blank")
    String objet;

    @NotBlank(message = "Message cannot be blank")
    String message;

    @NotNull(message = "Etat cannot be null")
    ReclamationsEtat etat;

    @NotNull(message = "Type cannot be null")
    ReclamationsType type;

    @NotNull(message = "Created Date cannot be null")
    LocalDateTime createdDate;

}