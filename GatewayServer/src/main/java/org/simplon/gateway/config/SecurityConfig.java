package org.simplon.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * This class configures the security with Keycloak for the webflux application.
 * @Author: Ayoub Ait Si AHmad
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    // This method configures the security filters for the webflux application and permits all requests.
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll()) // Permit all requests
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor()))); // Configure JWT authentication
        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable()); // Disable CSRF protection
        return serverHttpSecurity.build();
    }

    // This method defines the logic to extract granted authorities from the JWT token.
    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // Use KeycloakRoleConverter to extract authorities
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter); // Return converter for reactive streams
    }

}