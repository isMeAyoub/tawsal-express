package com.simplon.demandes.repository;

import com.simplon.demandes.model.entity.DemandeRamassage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface is for the DemandeRamassageRepository.
 * Should be used in the DemandeRamassage {@link com.simplon.demandes.model.entity.DemandeRamassage} entity.
 */
@Repository
public interface DemandeRamassageRepository extends JpaRepository<DemandeRamassage, Long> {
}