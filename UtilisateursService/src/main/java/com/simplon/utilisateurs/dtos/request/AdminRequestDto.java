package com.simplon.utilisateurs.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Admin}
 */
@Data
@NoArgsConstructor
public class AdminRequestDto implements Serializable {

    @NotBlank(message = "Mot de passe is required")
    String username;

    @NotBlank(message = "Prenom is required")
    String prenom;

    @NotBlank(message = "Nom is required")
    String nom;

    @Email(message = "Email should be valid")
    String email;

    String motDePasse;
}