package com.simplon.demandes.config;


import com.simplon.demandes.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The JpaAuditingConfiguration class is used to configure the JPA auditing in the application.
 * It's used in Spring Boot applications to enable JPA auditing and provide the current auditor of the application.
 *
 * @Author: Ayou Ait Si Ahmad
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}