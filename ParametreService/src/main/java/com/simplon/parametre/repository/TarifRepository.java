package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.Tarif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Tarif entities.
 * It extends JpaRepository to provide CRUD operations.
 *
 * @author Ayoub Ait Si Ahmad
 */
@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {

    Optional<Tarif> findByVilleRamassageNomVilleAndVilleLivraisonNomVille(String villeRamassage, String villeLivraison);

    @Query("SELECT t FROM Tarif t WHERE " +
            "(COALESCE(:VilleRamassage, null) IS NULL OR LOWER(t.villeRamassage.nomVille) LIKE LOWER(CONCAT('%', :VilleRamassage, '%'))) " +
            "AND (COALESCE(:VilleLivraison, null) IS NULL OR LOWER(t.villeLivraison.nomVille) LIKE LOWER(CONCAT('%', :VilleLivraison, '%'))) " +
            "AND (COALESCE(:search, null) IS NULL OR LOWER(t.villeRamassage.nomVille) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(t.villeLivraison.nomVille) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Tarif> findAllTarifs(@Param("search") String search, @Param("VilleRamassage") String VilleRamassage, @Param("VilleLivraison") String VilleLivraison, Pageable pageable);
}