package com.simplon.utilisateurs.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * The AuditorAwareImpl class is used to provide the current auditor of the application.
 * It's used in Spring Boot applications to provide the current user of the application.
 *
 * @Author: Ayou Ait Si Ahmad
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Ayoub Ait Si Ahmad");
    }
}