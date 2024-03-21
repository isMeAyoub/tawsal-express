package com.simplon.parametre.repository;

import com.simplon.parametre.model.entity.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The GeneralRepository interface is a Spring Data JPA Repository that provides methods to interact with the general table in the database.
 * It extends the JpaRepository interface included in Spring Data JPA and takes the General entity and its primary key as type arguments.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Repository
public interface GeneralRepository extends JpaRepository<General, Long> {
}