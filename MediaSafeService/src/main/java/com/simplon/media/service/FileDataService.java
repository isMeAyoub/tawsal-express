package com.simplon.media.service;

import com.simplon.media.dtos.response.FileDataResponseDto;
import com.simplon.media.model.entity.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service for {@link FileData}
 */
public interface FileDataService {
    FileDataResponseDto uploadImageToFileSystem(MultipartFile file) throws IOException;

    byte[] downloadImageFromFileSystem(Long fileId) throws IOException;
}
