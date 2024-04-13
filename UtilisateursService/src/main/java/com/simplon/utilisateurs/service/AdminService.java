package com.simplon.utilisateurs.service;

import com.simplon.utilisateurs.dtos.request.AdminRequestDto;
import com.simplon.utilisateurs.dtos.response.AdminResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface to manage Admin operations
 */
public interface AdminService {

    Page<AdminResponseDto> getAdmins(Pageable pageable);

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto,
                                 MultipartFile photo);

    AdminResponseDto getAdmin(Long adminId);

    AdminResponseDto updateAdmin(Long adminId, AdminRequestDto adminRequestDto,
                                 MultipartFile photo);

    void deleteAdmin(Long adminId);
}
