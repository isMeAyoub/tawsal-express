package com.simplon.utilisateurs.dtos.request;

import com.simplon.utilisateurs.model.enums.Banque;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Livreur}
 */
@Data
@NoArgsConstructor
public class LivreurRequestDto implements Serializable {

    @NotEmpty(message = "nomComple is required")
    String nomComplet;

    @Max(value = 10, message = "telephone must be at most 10 characters")
    @Min(value = 10, message = "telephone must be at least 10 characters")
    @NotEmpty(message = "telephone is required")
    String telephone;

    @Email(message = "email should be valid")
    @NotEmpty(message = "email is required")
    String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password should contain at least 8 characters, one uppercase, one lowercase, one digit, one special character and no whitespace")
    @NotEmpty(message = "motDePasse is required")
    String motDePasse;

    @NotNull(message = "banque is required")
    Banque banque;

    @Min(value = 24, message = "compteBancaire must be at least 24 characters")
    @Max(value = 24, message = "compteBancaire must be at most 24 characters")
    @NotEmpty(message = "compteBancaire is required")
    String compteBancaire;

    @NotEmpty(message = "adresse is required")
    String adresse;

    @Min(value = 8, message = "cin must be at least 8 characters")
    @Max(value = 7, message = "cin must be at most 8 characters")
    @NotEmpty(message = "cin is required")
    String cin;

    // TODO: be careful with the following fields
    String photoCinRecto;
    String photoCinVerso;
    String photoRib;

    @Positive(message = "fraisLivraison must be positive")
    @NotNull(message = "fraisLivraison is required")
    private Double fraisLivraison;

    @Positive(message = "fraisRetour must be positive")
    @NotNull(message = "fraisRetour is required")
    private Double fraisRetour;

    @Positive(message = "fraisRefus must be positive")
    @NotNull(message = "fraisRefus is required")
    private Double fraisRefus;

    @NotNull(message = "villeLivraisonId is required")
    Long villeLivraisonId;

    @NotNull(message = "zoneId is required")
    Long zoneId;
}