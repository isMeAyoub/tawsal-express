package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.VilleRamassage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for VilleRamassage entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface VilleRamassageRepository extends JpaRepository<VilleRamassage, Long> {
}