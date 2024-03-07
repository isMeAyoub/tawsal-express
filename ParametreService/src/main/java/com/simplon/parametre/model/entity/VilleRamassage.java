package com.simplon.parametre.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The VilleRamassage class represents a city where pickup is available.
 * It inherits from the Ville class for common properties.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "villeRamassage")
public class VilleRamassage extends Ville {

}
