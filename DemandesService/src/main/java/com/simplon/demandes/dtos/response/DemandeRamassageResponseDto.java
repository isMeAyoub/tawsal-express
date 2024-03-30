package com.simplon.demandes.dtos.response;

import com.simplon.demandes.model.enums.RamassageEtat;
import com.simplon.demandes.model.enums.RamassageType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.simplon.demandes.model.entity.DemandeRamassage}
 */
@Data
@NoArgsConstructor
public class DemandeRamassageResponseDto implements Serializable {

    @NotNull(message = "Updated Date cannot be null")
    Long demandeRamassageId;

    @NotEmpty(message = "remarque is required")
    String remarque;

    @NotEmpty(message = "adresse is required")
    String adresse;

    @NotEmpty(message = "telephone is required")
    String telephone;

    @NotNull(message = "ramassageEtat is required")
    RamassageEtat ramassageEtat;

    @NotNull(message = "ramassageType is required")
    RamassageType ramassageType;

    @NotNull(message = "Created Date cannot be null")
    LocalDateTime createdDate;
}