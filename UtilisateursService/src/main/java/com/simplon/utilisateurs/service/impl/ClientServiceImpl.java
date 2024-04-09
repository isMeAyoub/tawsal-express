package com.simplon.utilisateurs.service.impl;

import com.simplon.clients.media.FileResponseDto;
import com.simplon.clients.media.MediaClient;
import com.simplon.clients.notification.EventType;
import com.simplon.clients.notification.Notification;
import com.simplon.clients.notification.NotificationClient;
import com.simplon.clients.parametre.ParametreClient;
import com.simplon.clients.parametre.VilleRamassageResponseDto;
import com.simplon.clients.parametre.ZoneClientResponseDto;
import com.simplon.clients.parametre.ZoneResponseDto;
import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;
import com.simplon.utilisateurs.dtos.response.ClientResponseDto;
import com.simplon.utilisateurs.mapper.ClientMapper;
import com.simplon.utilisateurs.model.entity.Client;
import com.simplon.utilisateurs.repository.ClientRepository;
import com.simplon.utilisateurs.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final MediaClient mediaClient;
    private final ParametreClient parametreClient;
    private final NotificationClient notificationClient;
    private final KeycloakService keycloakService;

    @Value("${keycloak.roles.client}")
    private String ROLE_CLIENT;

    @Override
    public Page<ClientResponseDto> getInvalidClients(Pageable pageable) {
        log.info("Getting all not validated clients");
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<Client> clients = clientRepository.findByIsValidateFalse(sortedPage);
        log.debug("Clients found: {}", clients);
        Page<ClientResponseDto> clientResponseDtos = clients.map(clientMapper::toDto1);
        log.debug("Clients found: {}", clientResponseDtos);
        return clientResponseDtos;
    }

    @Override
    public Page<ClientResponseDto> getValidclients(Pageable pageable) {
        log.info("Getting all validated clients");
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        Page<Client> clients = clientRepository.findByIsValidateTrue(sortedPage);
        log.debug("Clients found: {}", clients);
        Page<ClientResponseDto> clientResponseDtos = clients.map(clientMapper::toDto1);

        log.debug("Clients found: {}", clientResponseDtos);
        return clientResponseDtos;
    }


    @Override
    public void createClient(ClientRequestDto clientRequestDto, MultipartFile photoProfile, MultipartFile photoCinRecto, MultipartFile photoCinVerso, MultipartFile photoRib) {

        log.info("Creating client: {}", clientRequestDto);

        // Check if client already exists
        checkIfClientExists(clientRequestDto);

        // Validate zone and villeRamassage
        validateZoneAndVilleRamassage(clientRequestDto.getZoneId(), clientRequestDto.getVilleRamassageId());

        // Create user in Keycloak
        String userKeycloakId = createUserInKeycloak(clientRequestDto);

        // Upload and save photos
        FileResponseDto photoProfileSaved = uploadPhoto(photoProfile);
        FileResponseDto photoCinRectoSaved = uploadPhoto(photoCinRecto);
        FileResponseDto photoCinVersoSaved = uploadPhoto(photoCinVerso);
        FileResponseDto photoRibSaved = uploadPhoto(photoRib);

        // Build and save client entity
        Client clientSaved = buildAndSaveClient(clientRequestDto, userKeycloakId, photoProfileSaved, photoCinRectoSaved, photoCinVersoSaved, photoRibSaved);

        // TODO: Implement email verification

        // Send notification to admin
        sendNewClientNotification(clientSaved);
    }

    @Override
    public ClientResponseDto getClient(Long clientId) {
        log.info("Getting client by id: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            log.error("Client not found");
            throw new EntityNotFoundException("Client not found");
        });

        ClientResponseDto clientResponseDto = clientMapper.toDto1(client);
        log.debug("Client found: {}", clientResponseDto);

        VilleRamassageResponseDto villeRamassage = parametreClient.getVilleRamassageById(client.getVilleRamassageId()).getBody();
        ZoneClientResponseDto zone = parametreClient.getZoneToClient(client.getZoneId()).getBody();

        clientResponseDto.setVilleRamassage(villeRamassage);
        clientResponseDto.setZone(zone);
        log.debug("Client found: {}", clientResponseDto);
        return clientResponseDto;
    }

    @Override
    public void updateClient(Long clientId, ClientRequestDto clientRequestDto, MultipartFile photoProfile, MultipartFile photoCinRecto, MultipartFile photoCinVerso, MultipartFile photoRib) {

    }

    @Override
    public void validateClient(Long clientId) {
        log.info("Validating client by id: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            log.error("Client not found");
            throw new EntityNotFoundException("Client not found with id: " + clientId);
        });

        // Validate user in Keycloak
        keycloakService.validateUserInKeycloak(client.getKeycloakId());

        // Validate client in database
        client.setIsValidate(true);
        client.setIsEnable(true);
        clientRepository.save(client);
        log.info("Client validated successfully");
    }

    @Override
    public void invalidateClient(Long clientId) {
        log.info("Invalidating client by id: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            log.error("Client not found");
            throw new EntityNotFoundException("Client not found with id: " + clientId);
        });

        // Delete user from Keycloak and client from database if not validated
        if (!client.getIsValidate()) {
            deleteClient(clientId);
            log.info("Client invalidated successfully");
            return;
        }

        log.info("Client invalidated successfully");
    }

    @Override
    public void suspendClient(Long clientId) {
        log.info("Suspending client by id: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            log.error("Client not found");
            throw new EntityNotFoundException("Client not found with id: " + clientId);
        });

        // suspend only if validated is true
        if (client.getIsValidate()) {
            // Suspend user in Keycloak
            keycloakService.suspendUserInKeycloak(client.getKeycloakId());

            // Suspend client in database
            client.setIsEnable(false);
            clientRepository.save(client);
            log.info("Client suspended successfully");
        }
    }


    @Override
    public void deleteClient(Long clientId) {
        log.info("Deleting client by id: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
            log.error("Client not found");
            throw new EntityNotFoundException("Client not found");
        });

        // Delete user from Keycloak
        keycloakService.deleteUserInKeycloak(client.getKeycloakId());

        // Delete client from database
        clientRepository.delete(client);
        log.info("Client deleted successfully");
    }

    private void checkIfClientExists(ClientRequestDto clientRequestDto) {
        clientRepository.findByTelephoneAndEmailAndCinAndNomEntrepriseAndRegistreCommerceAllIgnoreCase(
                clientRequestDto.getTelephone(),
                clientRequestDto.getEmail(),
                clientRequestDto.getCin(),
                clientRequestDto.getNomEntreprise(),
                clientRequestDto.getRegistreCommerce()).ifPresent(c -> {
            log.error("Client already exists: {}", c);
            throw new EntityExistsException("Client already exists");
        });
    }

    private void validateZoneAndVilleRamassage(Long zoneId, Long villeRamassageId) {
        try {
            parametreClient.getZone(zoneId);
            parametreClient.getVilleRamassageById(villeRamassageId);
        } catch (EntityNotFoundException e) {
            log.error("Zone or VilleRamassage not found");
            throw new EntityNotFoundException("Zone or VilleRamassage not found");
        }
    }

    private String createUserInKeycloak(ClientRequestDto clientRequestDto) {
        UserKeycloakRequestDto userKeycloakRequestDto = UserKeycloakRequestDto.builder()
                .username(clientRequestDto.getNomEntreprise())
                .firstName(clientRequestDto.getNom())
                .lastName(clientRequestDto.getPrenom())
                .email(clientRequestDto.getEmail())
                .password(clientRequestDto.getMotDePasse()).build();
        return keycloakService.createUserInKeycloak(userKeycloakRequestDto);
    }

    private FileResponseDto uploadPhoto(MultipartFile photo) {
        return mediaClient.uploadImageToFirebase(photo).getBody();
    }

    private Client buildAndSaveClient(ClientRequestDto clientRequestDto,
                                      String userKeycloakId,
                                      FileResponseDto photoProfileSaved,
                                      FileResponseDto photoCinRectoSaved,
                                      FileResponseDto photoCinVersoSaved,
                                      FileResponseDto photoRibSaved) {

        Client client = Client.builder()
                .nom(clientRequestDto.getNom())
                .prenom(clientRequestDto.getPrenom())
                .nomEntreprise(clientRequestDto.getNomEntreprise())
                .role(ROLE_CLIENT).keycloakId(userKeycloakId)
                .photoProfile(photoProfileSaved.getFilePath())
                .telephone(clientRequestDto.getTelephone())
                .email(clientRequestDto.getEmail())
                .banque(clientRequestDto.getBanque())
                .adresse(clientRequestDto.getAdresse())
                .cin(clientRequestDto.getCin())
                .photoCinRecto(photoCinRectoSaved.getFilePath())
                .photoCinVerso(photoCinVersoSaved.getFilePath())
                .compteBancaire(clientRequestDto.getCompteBancaire())
                .photoRib(photoRibSaved.getFilePath())
                .registreCommerce(clientRequestDto.getRegistreCommerce())
                .website(clientRequestDto.getWebsite())
                .typeEntreprise(clientRequestDto.getTypeEntreprise())
                .villeRamassageId(clientRequestDto.getVilleRamassageId())
                .zoneId(clientRequestDto.getZoneId())
                .isEnable(false)
                .isValidate(false)
                .build();
        return clientRepository.save(client);
    }

    private void sendNewClientNotification(Client clientSaved) {
        Notification notification = Notification.builder().title("Nouveau client").message("Un nouveau client a été créé").eventType(EventType.NOUVEAU_UTILISATEUR).triggerBy(clientSaved.getCreatedBy()).build();
        log.info("Sending new client notification: {}", notification);
        notificationClient.sendNotification(notification);
        log.info("New client notification sent successfully");
    }
}