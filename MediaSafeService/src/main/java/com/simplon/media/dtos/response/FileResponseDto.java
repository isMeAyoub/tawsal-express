package com.simplon.media.dtos.response;

import com.simplon.media.model.entity.File;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link File}
 */
@Data
@NoArgsConstructor
public class FileResponseDto implements Serializable {
    @NotNull(message = "fileDataId is required")
    Long fileId;
    @NotEmpty(message = "fileName is required")
    String fileName;
    @NotEmpty(message = "fileType is required")
    String fileType;
    @NotEmpty(message = "filePath is required")
    String filePath;
}