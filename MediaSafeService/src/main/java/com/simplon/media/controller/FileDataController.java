package com.simplon.media.controller;

import com.simplon.media.dtos.response.FileResponseDto;
import com.simplon.media.service.FileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * This endpoint is used to upload an image to the firebase storage
     *
     * @param file
     * @return FileDataResponseDto
     */
    @PostMapping
    public ResponseEntity<FileResponseDto> uploadImageToFirebase(@RequestParam("image") MultipartFile file) {
        log.info("Request to upload image to firebase");
        FileResponseDto fileResponseDto = fileDataService.uploadImageToFirebase(file);
        log.debug("Image uploaded successfully to firebase , fileResponseDto: {}", fileResponseDto);
        return ResponseEntity.ok(fileResponseDto);
    }
}
