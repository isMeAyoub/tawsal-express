package com.simplon.utilisateurs.service.impl;

import com.simplon.clients.media.FileResponseDto;
import com.simplon.clients.media.MediaClient;
import com.simplon.utilisateurs.dtos.request.ClientRequestDto;
import com.simplon.utilisateurs.dtos.request.UserKeycloakRequestDto;
import com.simplon.utilisateurs.mapper.ClientMapper;
import com.simplon.utilisateurs.model.entity.Client;
import com.simplon.utilisateurs.repository.ClientRepository;
import com.simplon.utilisateurs.service.ClientService;
import com.simplon.utilisateurs.service.KeycloakService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final MediaClient mediaClient;
    private final KeycloakService keycloakService;

    @Override
    public void createClient(ClientRequestDto clientRequestDto,
                             MultipartFile photoProfile,
                             MultipartFile photoCinRecto,
                             MultipartFile photoCinVerso,
                             MultipartFile photoRib) {
        log.info("Creating client: {}", clientRequestDto);
        Client client = clientMapper.toEntity(clientRequestDto);
        // TODO: check if the client already exists
        try {
            Client searchClient = clientRepository.findByNomIgnoreCaseOrPrenomIgnoreCaseOrTelephoneIgnoreCaseOrCinIgnoreCaseOrEmailIgnoreCaseOrNomEntrepriseIgnoreCaseOrRegistreCommerceIgnoreCase(
                    client.getNom(),
                    client.getPrenom(),
                    client.getTelephone(),
                    client.getCin(),
                    client.getEmail(),
                    client.getNomEntreprise(),
                    client.getRegistreCommerce()
            );
        } catch (EntityExistsException e) {
            log.error("Client already exists: {}", clientRequestDto);
            throw new EntityExistsException("Client already exists with the same information");
        }
        // TODO: add the client to keycloak
        UserKeycloakRequestDto userKeycloakRequestDto = UserKeycloakRequestDto.builder()
                .username(clientRequestDto.getEmail())
                .firstName(clientRequestDto.getNom())
                .lastName(clientRequestDto.getPrenom())
                .email(clientRequestDto.getEmail())
                .password(clientRequestDto.getMotDePasse())
                .build();

        keycloakService.createUserInKeycloak(userKeycloakRequestDto);

        // TODO: save photos in media service
        FileResponseDto photoProfileSaved = mediaClient.uploadImageToFirebase(photoProfile).getBody();
        FileResponseDto photoCinRectoSaved = mediaClient.uploadImageToFirebase(photoCinRecto).getBody();
        FileResponseDto photoCinVersoSaved = mediaClient.uploadImageToFirebase(photoCinVerso).getBody();
        FileResponseDto photoRibSaved = mediaClient.uploadImageToFirebase(photoRib).getBody();
        // TODO: save the photos using media service
        Client clientBuild = Client.builder()
                .nom(clientRequestDto.getNom())
                .prenom(clientRequestDto.getPrenom())
                .nomEntreprise(clientRequestDto.getNomEntreprise())
                // TODO: add the roles
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
                .nomEntreprise(clientRequestDto.getNomEntreprise())
                .registreCommerce(clientRequestDto.getRegistreCommerce())
                .website(clientRequestDto.getWebsite())
                .typeEntreprise(clientRequestDto.getTypeEntreprise())
                .villeRamassageId(clientRequestDto.getVilleRamassageId())
                .zoneId(clientRequestDto.getZoneId())
                .build();
        // TODO: email verification
        // TODO: add the client to the database
        clientRepository.save(clientBuild);
        // TODO: send notification to the admin
    }

    @Override
    public void updateClient(Long clientId, ClientRequestDto clientRequestDto, MultipartFile photoProfile, MultipartFile photoCinRecto, MultipartFile photoCinVerso, MultipartFile photoRib) {

    }

    @Override
    public void validateClient(Long clientId) {

    }

    @Override
    public void invalidateClient(Long clientId) {

    }

    @Override
    public void deleteClient(Long clientId) {

    }

}
