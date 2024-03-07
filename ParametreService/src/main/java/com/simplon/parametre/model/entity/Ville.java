package com.simplon.parametre.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Represents a base class for city entities with common properties.
 * This class is not mapped to a database table itself and serves as an inheritance base.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Getter
@SuperBuilder
@RequiredArgsConstructor
@MappedSuperclass
public class Ville {

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
