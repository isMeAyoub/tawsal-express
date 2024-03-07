package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Zone entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}