package com.simplon.utilisateurs.dtos.response;

import com.simplon.clients.parametre.VilleRamassageResponseDto;
import com.simplon.clients.parametre.ZoneClientResponseDto;
import com.simplon.clients.parametre.ZoneResponseDto;
import com.simplon.utilisateurs.model.enums.Banque;
import com.simplon.utilisateurs.model.enums.TypeEntreprise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Client}
 */
@Data
@NoArgsConstructor
public class ClientResponseDto implements Serializable {

    @NotNull(message = "createdDate is required")
    LocalDateTime createdDate;

    @NotNull(message = "utilisateurId is required")
    Long utilisateurId;

    @NotBlank(message = "prenom is required")
    String prenom;

    @NotBlank(message = "nom is required")
    String nom;

    @NotBlank(message = "role is required")
    String role;

    @NotBlank(message = "photoProfile is required")
    String photoProfile;

    @NotBlank(message = "telephone is required")
    String telephone;

    @NotBlank(message = "email is required")
    String email;

    @NotNull(message = "banque is required")
    Banque banque;

    @NotBlank(message = "adresse is required")
    String adresse;

    @NotBlank(message = "cin is required")
    String cin;

    @NotBlank(message = "photoCinRecto is required")
    String photoCinRecto;

    @NotBlank(message = "photoCinVerso is required")
    String photoCinVerso;

    @NotBlank(message = "compteBancaire is required")
    String compteBancaire;

    @NotBlank(message = "photoRib is required")
    String photoRib;

    @NotBlank(message = "nomEntreprise is required")
    String nomEntreprise;

    @NotBlank(message = "website is required")
    String website;

    @NotNull(message = "typeEntreprise is required")
    TypeEntreprise typeEntreprise;

    @NotBlank(message = "registreCommerce is required")
    String registreCommerce;

    @NotNull(message = "villeRamassage is required")
    VilleRamassageResponseDto villeRamassage;

    @NotNull(message = "zone is required")
    ZoneClientResponseDto zone;

    @NotNull(message = "isEnable is required")
    Boolean isEnable;

    @NotNull(message = "isValidate is required")
    Boolean isValidate;
}