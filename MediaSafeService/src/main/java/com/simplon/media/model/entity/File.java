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
@Table(name = "file")
public class File {
    @Id
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_sequence")
    @SequenceGenerator(name = "file_sequence", sequenceName = "file_sequence", allocationSize = 1)
    private Long fileId;

    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Column(name = "fileType", nullable = false)
    private String fileType;

    @Column(name = "filePath", nullable = false)
    private String filePath;
}
