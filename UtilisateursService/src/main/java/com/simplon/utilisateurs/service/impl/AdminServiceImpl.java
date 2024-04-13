package com.simplon.utilisateurs.service.impl;

import com.simplon.clients.media.FileResponseDto;
import com.simplon.clients.media.MediaClient;
import com.simplon.clients.notification.EventType;
import com.simplon.clients.notification.Notification;
import com.simplon.clients.notification.NotificationClient;
import com.simplon.utilisateurs.dtos.request.AdminRequestDto;
import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;
import com.simplon.utilisateurs.dtos.response.AdminResponseDto;
import com.simplon.utilisateurs.mapper.AdminMapper;
import com.simplon.utilisateurs.model.entity.Admin;
import com.simplon.utilisateurs.repository.AdminRepository;
import com.simplon.utilisateurs.service.AdminService;
import com.simplon.utilisateurs.service.KeycloakService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final KeycloakService keycloakService;
    private final MediaClient mediaClient;
    private final NotificationClient notificationClient;

    @Value("${keycloak.roles.admin}")
    private String ROLE_ADMIN;

    /**
     * Get admins from the database
     *
     * @param pageable
     * @return Page<AdminResponseDto>
     */
    @Override
    public Page<AdminResponseDto> getAdmins(Pageable pageable) {
        log.info("Getting admins from the database");
        Pageable sortedPage = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<Admin> admins = adminRepository.findAll(sortedPage);
        log.debug("Admins found: {}", admins);
        Page<AdminResponseDto> adminsResponse = admins.map(adminMapper::toDto1);
        log.info("Admins found: {}", adminsResponse);
        return adminsResponse;
    }

    /**
     * Create admin in the database and keycloak
     *
     * @param adminRequestDto
     * @param photo
     * @return AdminResponseDto
     */
    @Override
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto,
                                        MultipartFile photo) {

        log.info("Creating admin: {}", adminRequestDto);

        // map admin request to admin entity
        Admin admin = adminMapper.toEntity(adminRequestDto);

        // check the admin existence in the database
        checkIfAdminExists(admin);

        // create admin in keycloak
        String keycloakId = keycloakService.createUserInKeycloak(buildUserKeycloakRequestDto(adminRequestDto));

        // upload photo to firebase
        FileResponseDto fileResponseDto = uploadPhoto(photo);

        // save admin in the database
        Admin adminSaved = buildAndSaveAdmin(admin, keycloakId, fileResponseDto);

        // TODO: send email to verify the account

        // send notification
        sendNewClientNotification(adminSaved);

        return adminMapper.toDto1(adminSaved);
    }

    /**
     * Update admin in the database and keycloak
     *
     * @param adminId
     * @param adminRequestDto
     * @param photo
     * @return AdminResponseDto
     */
    @Override
    public AdminResponseDto updateAdmin(Long adminId,
                                        AdminRequestDto adminRequestDto,

                                        MultipartFile photo) {
        log.info("Updating admin with id: {}", adminId);
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> {
            log.error("Admin not found");
            throw new EntityNotFoundException("Admin not found");
        });

        // map admin request to admin entity
        Admin adminToUpdate = adminMapper.toEntity(adminRequestDto);

        // check the admin existence in the database
        checkIfAdminExistsForUpdate(adminToUpdate);

        // update admin in keycloak
        keycloakService.updateUserInKeycloak(buildUserKeycloakRequestDto(adminRequestDto), admin.getKeycloakId());

        // upload photo to firebase
        FileResponseDto fileResponseDto = uploadPhoto(photo);

        // save admin in the database
        Admin adminSaved = buildAndSaveAdmin(adminToUpdate, admin.getKeycloakId(), fileResponseDto);

        return adminMapper.toDto1(adminSaved);
    }

    /**
     * Get admin by id
     *
     * @param adminId
     * @return AdminResponseDto
     */
    @Override
    public AdminResponseDto getAdmin(Long adminId) {
        log.info("Getting admin with id: {}", adminId);
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> {
            log.error("Admin not found");
            throw new EntityNotFoundException("Admin not found");
        });

        return adminMapper.toDto1(admin);
    }

    /**
     * Delete admin by id
     *
     * @param adminId
     */
    @Override
    public void deleteAdmin(Long adminId) {
        log.info("Deleting admin with id: {}", adminId);
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> {
            log.error("Admin not found");
            throw new EntityNotFoundException("Admin not found");
        });

        // delete admin from keycloak
        keycloakService.deleteUserInKeycloak(admin.getKeycloakId());

        // delete admin from the database
        adminRepository.delete(admin);
        log.info("Admin deleted successfully");
    }

    /**
     * Check if the admin exists in the database
     *
     * @param admin
     */
    private void checkIfAdminExists(Admin admin) {
        log.debug("admin username: {} and email: {}", admin.getUsername(), admin.getEmail());
        adminRepository.findByEmailAndUsernameAllIgnoreCase(
                admin.getEmail(),
                admin.getUsername()).ifPresent(c -> {
            log.error("Admin already exists");
            throw new EntityExistsException("Admin already exists");
        });
    }

    /**
     * Check if the admin exists in the database for update
     *
     * @param admin
     */
    private void checkIfAdminExistsForUpdate(Admin admin) {
        log.debug("admin username: {} and email: {}", admin.getUsername(), admin.getEmail());
        adminRepository.findByEmailAndUsernameAndAdminIdNotAllIgnoreCase(
                admin.getEmail(),
                admin.getUsername(),
                admin.getAdminId()).ifPresent(c -> {
            log.error("Admin already exists");
            throw new EntityExistsException("Admin already exists");
        });
    }

    /**
     * Build user keycloak request dto
     *
     * @param adminRequestDto
     * @return UserKeycloakRequestDto
     */
    private UserKeycloakRequestDto buildUserKeycloakRequestDto(AdminRequestDto adminRequestDto) {
        return UserKeycloakRequestDto.builder()
                .username(adminRequestDto.getUsername())
                .email(adminRequestDto.getEmail())
                .firstName(adminRequestDto.getPrenom())
                .lastName(adminRequestDto.getNom())
                .password(adminRequestDto.getMotDePasse())
                .role(ROLE_ADMIN)
                .enabled(true)
                .build();
    }

    /**
     * Upload photo to firebase
     *
     * @param photo
     * @return FileResponseDto
     */
    private FileResponseDto uploadPhoto(MultipartFile photo) {
        return mediaClient.uploadImageToFirebase(photo).getBody();
    }

    /**
     * Build and save the admin in the database
     *
     * @param adminReq        the admin request
     * @param keycloakId      the keycloak id
     * @param fileResponseDto the file response dto
     * @return Admin
     */
    private Admin buildAndSaveAdmin(Admin adminReq,
                                    String keycloakId,
                                    FileResponseDto fileResponseDto) {

        Admin admin = Admin.builder()
                .keycloakId(keycloakId)
                .prenom(adminReq.getPrenom())
                .nom(adminReq.getNom())
                .role(ROLE_ADMIN)
                .photoProfile(fileResponseDto.getFilePath())
                .email(adminReq.getEmail())
                .username(adminReq.getUsername())
                .build();

        return adminRepository.save(admin);
    }

    /**
     * Send notification that a new admin has been created
     *
     * @param adminSaved
     */
    private void sendNewClientNotification(Admin adminSaved) {
        Notification notification = Notification.builder().title("Nouveau Admin").message("Un nouveau admin a été créé")
                .eventType(EventType.NOUVEAU_UTILISATEUR)
                .triggerBy(adminSaved.getCreatedBy())
                .build();
        log.info("Sending new admin notification");
        notificationClient.sendNotification(notification);
        log.info("New admin notification sent successfully");
    }

}
