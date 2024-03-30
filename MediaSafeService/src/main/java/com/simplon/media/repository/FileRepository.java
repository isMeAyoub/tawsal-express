package com.simplon.media.repository;

import com.simplon.media.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository for {@link File}
 */
@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}