package com.simplon.utilisateurs.util;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    private Keycloak keycloak;

    private String SERVER_URL = "http://localhost:8080/";
    private String REALM = "master";
    private String CLIENT_ID = "tawsal-express";
    private String GRANT_TYPE = "password";
    private String USERNAME = "admin";
    private String PASSWORD = "admin";

    public Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .grantType(GRANT_TYPE)
                    .realm(REALM)
                    .clientId(CLIENT_ID)
                    .username(USERNAME)
                    .password(PASSWORD)
                    .build();
        }
        return keycloak;
    }
}
