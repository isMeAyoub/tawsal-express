package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.AdminRequestDto;
import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;

/**
 * Interface to manage Keycloak operations
 */
public interface KeycloakService {
    String createUserInKeycloak(UserKeycloakRequestDto userKeycloakRequestDto);

    void updateUserInKeycloak(UserKeycloakRequestDto userRequest, String userId);

    void deleteUserInKeycloak(String userId);

    void validateUserInKeycloak(String userId);

    void suspendUserInKeycloak(String userId);
}
