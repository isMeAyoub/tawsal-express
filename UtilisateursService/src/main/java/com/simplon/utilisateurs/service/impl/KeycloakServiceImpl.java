package com.simplon.utilisateurs.service.impl;

import com.simplon.utilisateurs.dtos.request.AdminRequestDto;
import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;
import com.simplon.utilisateurs.service.KeycloakService;
import com.simplon.utilisateurs.util.KeycloakUtil;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private final KeycloakUtil keycloakUtil;

    /**
     * Create user in Keycloak with given user request
     *
     * @param userRequest
     * @return user ID
     */
    @Override
    public String createUserInKeycloak(UserKeycloakRequestDto userRequest) {
        log.info("Creating user in Keycloak: {}", userRequest.getUsername());

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = buildUserRepresentation(userRequest);

        Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            log.error("Failed to create user in Keycloak");
            throw new RuntimeException("Keycloak user creation failed");
        }

        String userId = CreatedResponseUtil.getCreatedId(response);
        assignRoleAndGroupToUser(userId, userRequest.getRole());
        log.info("User created successfully in Keycloak with ID: {}", userId);
        return userId;
    }

    /**
     * Get user from Keycloak by username
     *
     * @param userRequest
     * @param userId
     */
    @Override
    public void updateUserInKeycloak(UserKeycloakRequestDto userRequest, String userId) {
        log.info("Updating user in Keycloak: {}", userId);

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = usersResource.get(userId).toRepresentation();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setEnabled(userRequest.getEnabled());
        user.setEmailVerified(false);

        try {
            usersResource.get(userId).update(user);
            assignRoleAndGroupToUser(userId, userRequest.getRole());
            log.info("User updated successfully in Keycloak with ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to update user in Keycloak", e);
            throw new RuntimeException("Keycloak user update failed", e);
        }
    }

    /**
     * Build user representation
     *
     * @param userRequest
     * @return
     */
    private UserRepresentation buildUserRepresentation(UserKeycloakRequestDto userRequest) {
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(userRequest.getPassword());
        passwordCredential.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCredentials(Arrays.asList(passwordCredential));

        user.setEnabled(userRequest.getEnabled());
        user.setEmailVerified(false);

        return user;
    }

    /**
     * Assign role to user and delete all other roles
     *
     * @param userId
     * @param role
     */
    private void assignRoleAndGroupToUser(String userId, String role) {
        // Get UserResource
        UserResource userResource = keycloakUtil.getRealmResource().users().get(userId);

        // Get all realm-level roles of the user
        List<RoleRepresentation> userRoles = userResource.roles().realmLevel().listAll();

        // Remove all roles
        userResource.roles().realmLevel().remove(userRoles);

        // Add new role
        RoleRepresentation roleRepresentationToAdd = keycloakUtil.getRealmResource().roles().get(role).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentationToAdd));

    }

    @Override
    public void deleteUserInKeycloak(String userId) {
        log.info("Deleting user in Keycloak: {}", userId);

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        Response response = usersResource.delete(userId);

        if (response.getStatus() != 204) {
            log.error("Failed to delete user in Keycloak");
            throw new RuntimeException("Keycloak user deletion failed");
        }

        log.info("User deleted successfully in Keycloak with ID: {}", userId);
    }

    @Override
    public void validateUserInKeycloak(String userId) {
        log.info("Validating user in Keycloak: {}", userId);

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = usersResource.get(userId).toRepresentation();
        user.setEnabled(true);

        try {
            usersResource.get(userId).update(user);
            log.info("User validated successfully in Keycloak with ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to validate user in Keycloak", e);
            throw new RuntimeException("Keycloak user validation failed", e);
        }
    }

    @Override
    public void suspendUserInKeycloak(String userId) {
        log.info("Suspending user in Keycloak: {}", userId);

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = usersResource.get(userId).toRepresentation();
        user.setEnabled(false);

        try {
            usersResource.get(userId).update(user);
            log.info("User suspended successfully in Keycloak with ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to suspend user in Keycloak", e);
            throw new RuntimeException("Keycloak user suspension failed", e);
        }
    }

}