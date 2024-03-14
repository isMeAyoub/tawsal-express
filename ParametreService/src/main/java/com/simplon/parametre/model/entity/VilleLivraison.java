package com.simplon.parametre.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The VilleLivraison class represents a city where delivery is available.
 * It inherits from the Ville class for common properties.
 * It also has a ManyToOne relationship with the Zone entity.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "villeLivraison")
public class VilleLivraison extends Ville {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "zoneId",
            nullable = false)
    private Zone zone;
}
