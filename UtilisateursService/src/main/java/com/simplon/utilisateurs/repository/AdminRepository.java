package com.simplon.utilisateurs.repository;

import com.simplon.utilisateurs.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmailAndUsernameAllIgnoreCase(String email, String username);

    Optional<Admin> findByEmailAndUsernameAndAdminIdNotAllIgnoreCase(String email, String username, Long adminId);
}