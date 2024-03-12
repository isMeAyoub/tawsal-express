package org.simplon.gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class converts JWT claims to a collection of granted authorities.
 * @Author: Ayoub Ait Si AHmad
 */
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    // This method converts JWT claims to a collection of granted authorities.
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        // Extracting realm access claims from JWT.
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
        // Check if realm access claims exist.
        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if no realm access claims found.
        }
        // Extracting roles from realm access claims and mapping them to SimpleGrantedAuthority.
        Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
                .stream().map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return returnValue; // Return the collection of granted authorities.
    }
}
