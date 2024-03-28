package com.simplon.utilisateurs.dtos.request;

import com.simplon.utilisateurs.model.enums.Banque;
import com.simplon.utilisateurs.model.enums.TypeEntreprise;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Client}
 */
@Value
public class ClientRequestDto implements Serializable {

    // Personal information
    @NotBlank(message = "Nom complet is required")
    String nomComplet;

    @Max(value = 10, message = "Telephone must be 10 digits")
    @Min(value = 10, message = "Telephone must be 10 digits")
    @NotBlank(message = "Telephone is required")
    String telephone;

    @Email(message = "Email should be valid")
    String email;

    @NotNull(message = "Banque is required")
    Banque banque;

    @NotBlank(message = "Adresse is required")
    String adresse;

    @Max(value = 7, message = "CIN must be 7 digits")
    @NotBlank(message = "CIN is required")
    String cin;

    @NotNull(message = "Ville ramassage is required")
    Long villeRamassageId;

    @NotNull(message = "Zone is required")
    Long zoneId;

    @NotBlank(message = "Compte bancaire is required")
    String compteBancaire;

    // Company information
    @NotBlank(message = "Nom entreprise is required")
    String nomEntreprise;

    // optional
    String website;

    @NotNull(message = "Type entreprise is required")
    TypeEntreprise typeEntreprise;

    // optional
    String registreCommerce;

}