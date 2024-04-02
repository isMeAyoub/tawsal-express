package com.simplon.utilisateurs.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserKeycloakRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
