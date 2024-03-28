package com.simplon.demandes.model.entity;

import com.simplon.demandes.audit.Auditable;
import com.simplon.demandes.model.enums.ReclamationsEtat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Class if for the Reclamations entity.
 * Should be used in the Reclamations {@link com.simplon.demandes.model.entity.Reclamations} entity.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reclamations")
public class Reclamations extends Auditable<String> {
    @Id
    @Column(name = "reclamation_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reclamation_sequence")
    @SequenceGenerator(name = "reclamation_sequence", sequenceName = "reclamation_sequence", allocationSize = 1)
    private Long reclamationId;

    @Column(name = "objet", nullable = false)
    private String objet;

    @Column(name = "message", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat", nullable = false)
    private ReclamationsEtat etat;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
