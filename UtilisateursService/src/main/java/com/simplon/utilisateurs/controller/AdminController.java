package com.simplon.utilisateurs.controller;

import com.simplon.utilisateurs.dtos.request.AdminRequestDto;
import com.simplon.utilisateurs.dtos.response.AdminResponseDto;
import com.simplon.utilisateurs.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/utilisateurs/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<Page<AdminResponseDto>> getAllAdmins(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("Getting all admins");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<AdminResponseDto> admins = adminService.getAdmins(pageable);
        log.info("Admins found: {}", admins);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminResponseDto> getAdmin(@PathVariable Long adminId) {
        log.info("Getting admin with id: {}", adminId);
        AdminResponseDto adminResponseDto = adminService.getAdmin(adminId);
        log.info("Admin found: {}", adminResponseDto);
        return ResponseEntity.ok(adminResponseDto);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        log.info("Deleting admin with id: {}", adminId);
        adminService.deleteAdmin(adminId);
        log.info("Admin deleted");
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AdminResponseDto> createAdmin(
            @Valid @RequestPart("admin") AdminRequestDto adminRequestDto,
            @RequestPart(value = "photo") MultipartFile photo) {
        log.info("Creating admin");
        AdminResponseDto adminResponseDto = adminService.createAdmin(adminRequestDto, photo);
        log.info("Admin created: {}", adminResponseDto);
        return ResponseEntity.ok(adminResponseDto);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<AdminResponseDto> updateAdmin(
            @PathVariable Long adminId,
            @Valid @RequestPart("admin") AdminRequestDto adminRequestDto,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        log.info("Updating admin with id: {}", adminId);
        AdminResponseDto adminResponseDto = adminService.updateAdmin(adminId, adminRequestDto, photo);
        log.info("Admin updated: {}", adminResponseDto);
        return ResponseEntity.ok(adminResponseDto);
    }
}
