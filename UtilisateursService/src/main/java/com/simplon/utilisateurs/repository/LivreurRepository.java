package com.simplon.utilisateurs.repository;

import com.simplon.utilisateurs.model.entity.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {
}