package com.simplon.utilisateurs.model.entity;


import com.simplon.utilisateurs.model.enums.TypeEntreprise;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to define the attributes of the client
 * Client extends Utilisateur
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Utilisateur {

    @Column(name = "nom_entreprise", nullable = false)
    private String nomEntreprise;

    @Column(name = "site_web", nullable = false)
    private String website;

    @Column(name = "type_entreprise", nullable = false)
    private TypeEntreprise typeEntreprise;

    @Column(name = "registre_commerce", nullable = false)
    private String registreCommerce;

    // TODO: should make relation with ville ramassage
    @Column(name = "ville_ramassage_id", nullable = false)
    private Long villeRamassageId;

    // TODO: should make relation with colis, reclamations , demandesRamassage
}