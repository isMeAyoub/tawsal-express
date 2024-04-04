package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;

public interface KeycloakService {
    String createUserInKeycloak(UserKeycloakRequestDto userKeycloakRequestDto);
    void deleteUserInKeycloak(String userId);
    void validateUserInKeycloak(String userId);

    void suspendUserInKeycloak(String userId);
}
