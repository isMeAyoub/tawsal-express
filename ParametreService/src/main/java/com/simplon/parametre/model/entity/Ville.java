package com.simplon.parametre.model.entity;

import com.simplon.parametre.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Represents a base class for city entities with common properties.
 * This class is not mapped to a database table itself and serves as an inheritance base.
 *
 * @Author: Ayoub Ait Si Ahmad
 */

@Data
@SuperBuilder
@RequiredArgsConstructor
@MappedSuperclass
public class Ville extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long villeId;
    @Column(name = "reference", unique = true, nullable = false)
    private String reference;
    @Column(name = "nomVille", unique = true, nullable = false)
    private String nomVille;
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;
}