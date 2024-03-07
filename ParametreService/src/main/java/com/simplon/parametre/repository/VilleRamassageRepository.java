package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.VilleLivraison;
import com.simplon.parametre.model.entity.VilleRamassage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for VilleRamassage entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface VilleRamassageRepository extends JpaRepository<VilleRamassage, Long> {
    Optional<VilleRamassage> findByNomVilleIgnoreCaseOrReferenceIgnoreCase(String nomVille, String reference);
    @Query("SELECT v FROM VilleRamassage v WHERE (:search IS NULL OR (LOWER(v.nomVille) LIKE LOWER(:search) || '%' OR LOWER(v.reference) LIKE LOWER(:search) || '%'))")
    Page<VilleRamassage> searchVilleRamassage(@Param("search") String search, Pageable pageable);
}