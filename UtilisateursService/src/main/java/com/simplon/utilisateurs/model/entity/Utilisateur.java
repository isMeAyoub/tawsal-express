package com.simplon.utilisateurs.model.entity;

import com.simplon.utilisateurs.audit.Auditable;
import com.simplon.utilisateurs.model.enums.Banque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * This class is used only to define the common attributes of the different types of users in the application.
 *
 * @Author: Ayou Ait Si Ahmad
 */
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur extends Auditable<String> {

    @Id
    @Column(name = "utilisateur_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_id_seq")
    @SequenceGenerator(name = "utilisateur_id_seq", sequenceName = "utilisateur_id_seq", allocationSize = 1)
    private Long utilisateurId;

    @Column(name = "nom_complet", nullable = false ,unique = true)
    private String nomComplet;

    @Column(name = "photo_profile", nullable = false)
    private String photoProfile;

    @Column(name = "telephone", nullable = false, unique = true)
    private String telephone;

    @Column(name = "email", nullable = false ,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "password", nullable = false)
    private Banque banque;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "cin", nullable = false, unique = true)
    private String cin;

    @Column(name = "photo_cin_recto", nullable = false)
    private String photoCinRecto;

    @Column(name = "photo_cin_verso", nullable = false)
    private String photoCinVerso;

    @Column(name = "compte_bancaire", nullable = false)
    private String compteBancaire;

    @Column(name = "photo_rib", nullable = false)
    private String photoRib;
}
