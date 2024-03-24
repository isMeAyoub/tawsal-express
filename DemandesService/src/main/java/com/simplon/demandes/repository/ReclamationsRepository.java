package com.simplon.demandes.repository;

import com.simplon.demandes.model.entity.Reclamations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface is for the ReclamationsRepository.
 * Should be used in the Reclamations {@link com.simplon.demandes.model.entity.Reclamations} entity.
 */
@Repository
public interface ReclamationsRepository extends JpaRepository<Reclamations, Long> {
}