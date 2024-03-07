package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.VilleLivraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for VilleLivraison entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface VilleLivraisonRepository extends JpaRepository<VilleLivraison, Long> {
    Page<VilleLivraison> findAll(Pageable pageable);
}