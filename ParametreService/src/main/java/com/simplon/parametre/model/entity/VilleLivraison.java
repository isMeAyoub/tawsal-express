package com.simplon.parametre.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The VilleLivraison class represents a city where delivery is available.
 * It inherits from the Ville class for common properties.
 * It also has a ManyToOne relationship with the Zone entity.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "villeLivraison")
public class VilleLivraison extends Ville {

    @ManyToOne
    @JoinColumn(
            name = "zoneId",
            nullable = false)
    private Zone zone;
}
