package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.VilleLivraison;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for VilleLivraison entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface VilleLivraisonRepository extends JpaRepository<VilleLivraison, Long> {
    Page<VilleLivraison> findAll(Pageable pageable);
    Optional<VilleLivraison> findByNomVilleIgnoreCaseOrReferenceIgnoreCase(String nomVille , String reference);
    Optional<VilleLivraison> findByNomVilleIgnoreCaseOrReferenceIgnoreCaseAndVilleIdNot(String nomVille, String reference, Long villeId);

    @Query("SELECT v FROM VilleLivraison v JOIN v.zone z WHERE (:search IS NULL OR (LOWER(v.nomVille) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(v.reference) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(z.nomZone) LIKE LOWER(CONCAT('%', :search, '%'))))")
    Page<VilleLivraison> getALlVilleLivraisonAndSearch(@Param("search") String search, Pageable pageable);
}