package com.simplon.media.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Entity is used to store the file data in the database.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fileData")
public class FileData {
    @Id
    @Column(name = "fileDataId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileData_sequence")
    @SequenceGenerator(name = "fileData_sequence", sequenceName = "fileData_sequence", allocationSize = 1)
    private Long filaDataId;

    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Column(name = "fileType", nullable = false)
    private String fileType;

    @Column(name = "filePath", nullable = false)
    private String filePath;
}
