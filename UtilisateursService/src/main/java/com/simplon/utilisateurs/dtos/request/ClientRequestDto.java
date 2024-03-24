package com.simplon.utilisateurs.dtos.request;

import com.simplon.utilisateurs.model.enums.TypeEntreprise;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Client}
 */
@Data
@NoArgsConstructor
public class ClientRequestDto implements Serializable {

    @NotEmpty(message = "nomComplet is required")
    String nomComplet;

    @Max(value = 10, message = "telephone should be 10 characters")
    @Min(value = 10, message = "telephone should be 10 characters")
    @NotEmpty(message = "telephone is required")
    String telephone;

    @Email(message = "Email should be valid")
    String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password should contain at least 8 characters, one uppercase, one lowercase, one digit, one special character and no whitespace")
    @NotEmpty(message = "motDePasse is required")
    String motDePasse;

    @NotEmpty(message = "adresse is required")
    String adresse;

    @Max(value = 7, message = "cin should be 7 characters")
    @Min(value = 7, message = "cin should be 7 characters")
    @NotEmpty(message = "cin is required")
    String cin;

    @NotEmpty(message = "photoCinRecto is required")
    String nomEntreprise;

    @NotEmpty(message = "photoCinVerso is required")
    String website;

    @NotNull(message = "typeEntreprise is required")
    TypeEntreprise typeEntreprise;

    @NotEmpty(message = "registreCommerce is required")
    String registreCommerce;

    @NotNull(message = "villeId is required")
    Long villeRamassageId;
}