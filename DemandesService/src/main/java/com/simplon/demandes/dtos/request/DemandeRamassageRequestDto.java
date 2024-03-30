package com.simplon.demandes.dtos.request;

import com.simplon.demandes.model.enums.RamassageType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**
 * DTO for {@link com.simplon.demandes.model.entity.DemandeRamassage}
 */
@Data
@NoArgsConstructor
public class DemandeRamassageRequestDto implements Serializable {

    @NotEmpty(message = "remarque is required")
    String remarque;

    String adresse;

    String telephone;

    @NotNull(message = "email is required")
    RamassageType ramassageType;
}