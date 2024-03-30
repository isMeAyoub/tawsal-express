package com.simplon.clients.media;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This client is used for the communication with the media service.
 * It allows to upload and download files and much more.
 *
 * @Author: Ayou Ait Si Ahmad
 */
@FeignClient(value = "MEDIA")
public interface MediaClient {

    /**
     * This endpoint is used to upload an image to the file system
     * I had to change the RequestParam to RequestPart because the media service is expecting a RequestPart
     *
     * @param file
     * @return FileDataResponseDto
     */
    @PostMapping(value = "/api/v1/media/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileResponseDto> uploadImageToFirebase(@RequestPart("image") MultipartFile file);
}
