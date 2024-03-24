package com.simplon.clients.media;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class FileDataResponseDto implements Serializable {
    @NotNull(message = "fileDataId is required")
    Long filaDataId;
    @NotEmpty(message = "fileName is required")
    String fileName;
    @NotEmpty(message = "fileType is required")
    String fileType;
    @NotEmpty(message = "filePath is required")
    String filePath;
}