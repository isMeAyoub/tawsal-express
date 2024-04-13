package com.simplon.utilisateurs.model.entity;

import com.simplon.utilisateurs.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to define the attributes of the admin user in the application.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin extends Auditable<String> {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_id_seq")
    @SequenceGenerator(name = "admin_id_seq", sequenceName = "admin_id_seq", allocationSize = 1)
    private Long adminId;

    @Column(name = "keycloak_id", nullable = false, unique = true)
    private String keycloakId;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "photo_profile", nullable = false)
    private String photoProfile;

    @Column(name = "telephone", nullable = false, unique = true)
    private String email;

    @Column(name = "email", nullable = false, unique = true)
    private String username;
}
