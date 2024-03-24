package com.simplon.clients.media;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(value = "/api/v1/media/file")
    ResponseEntity<FileDataResponseDto> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException;
}
