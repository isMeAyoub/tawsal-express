package com.simplon.utilisateurs.repository;

import com.simplon.utilisateurs.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByNomCompletIgnoreCaseOrTelephoneIgnoreCaseOrCinIgnoreCaseOrEmailIgnoreCaseOrNomEntrepriseIgnoreCaseOrRegistreCommerceIgnoreCase(
            String nomComplet, String telephone, String cin, String email, String nomEntreprise, String registreCommerce);
}