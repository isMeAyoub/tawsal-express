package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.General}
 */
@Data
@NoArgsConstructor
public class GeneralResponseDto implements Serializable {

    @NotNull(message = "generalId is required")
    Long generalId;
    @NotEmpty(message = "logo is required")
    String logo;
    @NotEmpty(message = "favicon is required")
    String favicon;
    @NotEmpty(message = "nomEntreprise is required")
    String nomEntreprise;
    @NotEmpty(message = "webSite is required")
    String webSite;
    @NotEmpty(message = "adresseEntreprise is required")
    String adresseEntreprise;
    @NotEmpty(message = "telephoneEntreprise is required")
    String telephoneEntreprise;
    @NotEmpty(message = "emailEntreprise is required")
    @Email(message = "Email should be valid")
    String emailEntreprise;
    @NotEmpty(message = "rc is required")
    String monnaieApplication;
}