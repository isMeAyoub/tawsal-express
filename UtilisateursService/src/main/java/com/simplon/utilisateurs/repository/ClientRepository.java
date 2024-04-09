package com.simplon.utilisateurs.repository;

import com.simplon.utilisateurs.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByTelephoneAndEmailAndCinAndNomEntrepriseAndRegistreCommerceAllIgnoreCase(String telephone,
                                                                                                   String email,
                                                                                                   String cin,
                                                                                                   String nomEntreprise,
                                                                                                   String registreCommerce);

    @Query("select c from Client c where c.isValidate = false")
    Page<Client> findByIsValidateFalse( Pageable pageable);

    @Query("select c from Client c where c.isValidate = true")
    Page<Client> findByIsValidateTrue( Pageable pageable);
}