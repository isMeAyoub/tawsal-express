package com.simplon.parametre.model.entity;

import com.simplon.parametre.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Zone class represents a geographical area with its unique ID, name, and a boolean indicating if it's active or not.
 * It's a JPA entity class used in Spring Boot applications to map to a database table named "zone".
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "zone")
public class Zone extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "zoneId")
    private Long zoneId;
    @Column(name = "nomZone", unique = true, nullable = false)
    private String nomZone;
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "zone", fetch = FetchType.EAGER)
    private List<VilleLivraison> villesLivraison;
}