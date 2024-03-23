package com.simplon.media.controller;

import com.simplon.media.dtos.response.FileDataResponseDto;
import com.simplon.media.model.entity.FileData;
import com.simplon.media.service.FileDataService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This controller is used to manage the file data.
 * It allows to upload and download files.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/media/file")
public class FileDataController {

    private final FileDataService fileDataService;


    @PostMapping
    public ResponseEntity<FileDataResponseDto> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        log.info("file upload controller");
        FileDataResponseDto uploadImage = fileDataService.uploadImageToFileSystem(file);
        log.debug("file uploaded : " + uploadImage);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> downloadImageFromFileSystem(@NotNull @PathVariable Long fileId) throws IOException {
        log.info("file download controller");
        byte[] imageData = fileDataService.downloadImageFromFileSystem(fileId);
        log.debug("file downloaded : " + fileId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
