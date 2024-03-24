package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.General}
 */
@Data
@NoArgsConstructor
public class GeneralRequestDto implements Serializable {

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