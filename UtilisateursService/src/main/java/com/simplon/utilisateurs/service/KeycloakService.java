package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;

public interface KeycloakService {
    void createUserInKeycloak(UserKeycloakRequestDto userKeycloakRequestDto);
}
