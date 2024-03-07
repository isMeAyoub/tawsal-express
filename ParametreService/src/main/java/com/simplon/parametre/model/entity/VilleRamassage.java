package com.simplon.parametre.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The VilleRamassage class represents a city where pickup is available.
 * It inherits from the Ville class for common properties.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "villeRamassage")
public class VilleRamassage extends Ville {

}
