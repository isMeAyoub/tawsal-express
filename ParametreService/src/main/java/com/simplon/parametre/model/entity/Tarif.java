package com.simplon.parametre.model.entity;

import com.simplon.parametre.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

/**
 * The Tarif class represents a pricing structure with its unique ID, delivery price, return price, refusal price, and delivery delay.
 * It's a JPA entity class used in Spring Boot applications to map to a database table named "tarif".
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tarif")
public class Tarif extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tarifId")
    private Long tarifId;
    @Column(name = "prixLivraison", nullable = false)
    private Double prixLivraison;
    @Column(name = "prixRetour", nullable = false)
    private Double prixRetour;
    @Column(name = "prixRefuse", nullable = false)
    private Double prixRefuse;
    @Column(name = "delaiLivraison", nullable = false)
    private String delaiLivraison;

    @ManyToOne
    @JoinColumn(
            name = "villeLivraisonId",
            nullable = false
    )
    private VilleLivraison villeLivraison;
    @ManyToOne
    @JoinColumn(
            name = "villeRamassageId",
            nullable = false
    )
    private VilleRamassage villeRamassage;
}