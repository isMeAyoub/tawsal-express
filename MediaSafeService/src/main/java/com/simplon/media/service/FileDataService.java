package com.simplon.media.service;

import com.simplon.media.dtos.response.FileResponseDto;
import com.simplon.media.model.entity.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for {@link File}
 */
public interface FileDataService {
    FileResponseDto uploadImageToFirebase(MultipartFile multipartFile);
}
