package com.simplon.media.repository;

import com.simplon.media.model.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository for {@link FileData}
 */
@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByFileName(String fileName);
    Optional<FileData> findByFilaDataId(Long fileId);
}