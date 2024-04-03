package com.simplon.utilisateurs.service.impl;

import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;
import com.simplon.utilisateurs.service.KeycloakService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class KeycloakServiceImpl implements KeycloakService {

    private Keycloak keycloak;

    @Value("${keycloak.credentials.secret}")
    private String SECRETKEY;

    @Value("${keycloak.resource}")
    private String CLIENTID;

    @Value("${keycloak.server-url}")
    private String SERVERURL;

    @Value("${keycloak.realm}")
    private String REALM;

    @Value("${keycloak.username}")
    private String USERNAME;

    @Value("${keycloak.password}")
    private String PASSWORD;

    @Value("${keycloak.roles.client}")
    private String ROlE_CLIENT;

    public Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVERURL)
                    .realm(REALM)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(CLIENTID)
                    .username(USERNAME)
                    .password(PASSWORD)
                    .build();
        }
        return keycloak;
    }

    public RealmResource getRealmResource() {
        Keycloak keycloakInstance = getInstance();
        return keycloakInstance.realm(REALM);
    }

    @Override
    public void createUserInKeycloak(UserKeycloakRequestDto userKeycloakRequestDto) {
        RealmResource realmResource = getRealmResource();
        UsersResource usersResource = realmResource.users();

        // Create user representation
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userKeycloakRequestDto.getUsername());
        user.setFirstName(userKeycloakRequestDto.getFirstName());
        user.setLastName(userKeycloakRequestDto.getLastName());
        user.setEmail(userKeycloakRequestDto.getEmail());
        user.setEmailVerified(false);
        user.setEnabled(false);

        // Create password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userKeycloakRequestDto.getPassword());
        user.setCredentials(Arrays.asList(passwordCred));

        // Assign role to user
        user.setRealmRoles(Arrays.asList(ROlE_CLIENT));

       usersResource.create(user);
    }
}