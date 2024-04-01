package com.simplon.utilisateurs.dtos.request;

import com.simplon.utilisateurs.model.enums.Banque;
import com.simplon.utilisateurs.model.enums.TypeEntreprise;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Client}
 */
@Data
@NoArgsConstructor
public class ClientRequestDto implements Serializable {

    // Personal information
    @NotBlank(message = "Nom complet is required")
    String nomComplet;

    @Length(min = 10, max = 10, message = "Telephone must be 10 digits")
    @NotBlank(message = "Telephone is required")
    String telephone;

    @Email(message = "Email should be valid")
    String email;

    @NotNull(message = "Banque is required")
    Banque banque;

    @NotBlank(message = "Adresse is required")
    String adresse;

    @Length(min = 8, max = 8, message = "CIN must be 8 digits")
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