package com.simplon.media.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.simplon.media.dtos.response.FileResponseDto;
import com.simplon.media.mapper.FileMapper;
import com.simplon.media.model.entity.File;
import com.simplon.media.repository.FileRepository;
import com.simplon.media.service.FileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

/**
 * This Service is used to manage the file data.
 *
 * @Author: Ayou Ait Si Ahmad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileDataServiceImpl implements FileDataService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Value("${firebase.bucket}")
    private String BUCKET_NAME;

    @Value("${firebase.url}")
    private String FIREBASE_URL;

    @Value("${firebase.credentials}")
    private String FIREBASE_CREDENTIALS;

    /**
     * This method is used to upload an image to the firebase storage
     *
     * @param multipartFile
     * @return FileResponseDto
     */
    @Override
    public FileResponseDto uploadImageToFirebase(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            java.io.File file = this.convertToFile(multipartFile, fileName);
            String URL = this.uploadFile(multipartFile, file, fileName);
            file.delete();

            // Build the entity
            File fileEntity = File.builder()
                    .fileName(fileName)
                    .fileType(multipartFile.getContentType())
                    .filePath(URL)
                    .build();

            // Save the entity using the repository
            File savedFile = fileRepository.save(fileEntity);

            // Map the saved entity to FileResponseDto and return it
            return fileMapper.toDto(savedFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Image couldn't upload, Something went wrong", e);
        }
    }

    /**
     * This method is used to upload a file to the firebase storage
     * @param multipartFile
     * @param file
     * @param fileName
     * @return
     * @throws IOException
     */
    private String uploadFile(MultipartFile multipartFile, java.io.File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        InputStream inputStream = FileDataServiceImpl.class.getClassLoader().getResourceAsStream(FIREBASE_CREDENTIALS);
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        //  return the URL of the uploaded file in the firebase storage
        return String.format(FIREBASE_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    /**
     * This method is used to convert a MultipartFile to a File
     * @param multipartFile
     * @param fileName
     * @return
     * @throws IOException
     */
    private java.io.File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        java.io.File tempFile = new java.io.File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    /**
     * This method is used to get the extension of a file
     * @param fileName
     * @return
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}