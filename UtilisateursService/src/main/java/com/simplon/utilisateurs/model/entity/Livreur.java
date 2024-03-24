package com.simplon.utilisateurs.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to define the attributes of the delivery
 * Livreur extends Utilisateur
 *
 * @eAuthor: Ayoub Ait Si Ahmad
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livreurs")
@PrimaryKeyJoinColumn(name = "livreur_id")
public class Livreur extends Utilisateur {

    @Column(name = "frais_livraison", nullable = false)
    private Double fraisLivraison;

    @Column(name = "frais_retour", nullable = false)
    private Double fraisRetour;

    @Column(name = "frais_refus", nullable = false)
    private Double fraisRefus;

    @Column(name = "ville_livraison_id", nullable = false)
    private Long villeLivraisonId;

    @Column(name = "zone_id", nullable = false)
    private Long zoneId;
}
