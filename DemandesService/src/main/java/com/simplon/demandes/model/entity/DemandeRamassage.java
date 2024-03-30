package com.simplon.demandes.model.entity;

import com.simplon.demandes.audit.Auditable;
import com.simplon.demandes.model.enums.RamassageEtat;
import com.simplon.demandes.model.enums.RamassageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a DemandeRamassage entity.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demande_ramassage")
public class DemandeRamassage extends Auditable<String> {

    @Id
    @Column(name = "demande_ramassage_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "demande_ramassage_sequence")
    @SequenceGenerator(name = "demande_ramassage_sequence", sequenceName = "demande_ramassage_sequence", allocationSize = 1)
    private Long demandeRamassageId;

    @Column(name = "remarque", nullable = false)
    private String remarque;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;


    @Enumerated(EnumType.STRING)
    @Column(name = "ramassage_etat", nullable = false)
    private RamassageEtat ramassageEtat;

    @Enumerated(EnumType.STRING)
    @Column(name = "ramassage_type", nullable = false)
    private RamassageType ramassageType;

    // TODO: Add user_id relation
}