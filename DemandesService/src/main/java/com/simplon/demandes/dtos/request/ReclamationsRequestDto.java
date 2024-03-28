package com.simplon.demandes.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.demandes.model.entity.Reclamations}
 */
@Data
@NoArgsConstructor
public class ReclamationsRequestDto implements Serializable {
    @NotBlank(message = "Objet cannot be blank")
    String objet;

    @NotBlank(message = "Message cannot be blank")
    String message;
}