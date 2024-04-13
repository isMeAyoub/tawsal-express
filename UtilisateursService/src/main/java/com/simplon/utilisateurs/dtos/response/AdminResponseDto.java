package com.simplon.utilisateurs.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.utilisateurs.model.entity.Admin}
 */
@Data
@NoArgsConstructor
public class AdminResponseDto implements Serializable {

    @NotNull(message = "AdminId is required")
    Long adminId;

    @NotBlank(message = "Prenom is required")
    String prenom;

    @NotBlank(message = "Nom is required")
    String nom;

    @NotBlank(message = "Role is required")
    String role;

    @NotBlank(message = "Photo profile is required")
    String photoProfile;

    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Username is required")
    String username;
}