package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Tarif entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {
}