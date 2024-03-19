package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Zone entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Optional<Zone> findByNomZoneIgnoreCase(String nomZone);

    Page<Zone> findAll(Pageable pageable);

    Optional<Zone> findByNomZoneAndZoneIdNot(String nomZone, Long zoneId);

    @Query("SELECT z FROM Zone z WHERE LOWER(z.nomZone) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Zone> findAllByNomZone(Pageable pageable, @Param("search") String search);
}