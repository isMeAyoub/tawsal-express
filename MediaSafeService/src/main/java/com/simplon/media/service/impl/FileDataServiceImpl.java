package com.simplon.media.service.impl;

import com.simplon.media.dtos.response.FileDataResponseDto;
import com.simplon.media.mapper.FileDataMapper;
import com.simplon.media.model.entity.FileData;
import com.simplon.media.repository.FileDataRepository;
import com.simplon.media.service.FileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

/**
 * This service is used to manage the file data.
 * It allows to upload and download files.
 *
 * @Author: Ayou Ait Si Ahmad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileDataServiceImpl implements FileDataService {
    private final FileDataRepository fileDataRepository;
    private final FileDataMapper fileDataMapper;

    private final String FOLDER_PATH = "C:\\Users\\SetupGame\\Desktop\\File Data\\";

    public FileDataResponseDto uploadImageToFileSystem(MultipartFile file) throws IOException {
        log.info(" file upload service");
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        log.info(" file path : " + filePath);
        FileData fileData = fileDataRepository.save(FileData.builder().fileName(file.getOriginalFilename()).fileType(file.getContentType()).filePath(filePath).build());
        log.debug("file data : " + fileData);
        file.transferTo(new File(filePath));

        if (fileData != null) {
            log.info("file uploaded successfully");
            return fileDataMapper.toDto(fileData);
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(Long fileId) throws IOException {
        log.info(" file download service");
        Optional<FileData> fileData = fileDataRepository.findByFilaDataId(fileId);
        log.debug("file data : " + fileData);
        String filePath = fileData.get().getFilePath();
        log.debug("file path : " + filePath);
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        log.info("file downloaded successfully");
        return images;
    }
}
