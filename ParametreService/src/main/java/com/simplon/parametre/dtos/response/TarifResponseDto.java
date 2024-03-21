package com.simplon.parametre.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Tarif}
 */
@Data
@NoArgsConstructor
public class TarifResponseDto implements Serializable {
    @NotNull(message = "tarifId is required")
    Long tarifId;
    @NotNull(message = "prixLivraison is required")
    Double prixLivraison;
    @NotNull(message = "prixRetour is required")
    Double prixRetour;
    @NotNull(message = "prixRefuse is required")
    Double prixRefuse;
    @NotNull(message = "delaiLivraison is required")
    String delaiLivraison;
    @NotNull(message = "villeLivraison is required")
    TarifResponseDto.VilleLivraisonDto villeLivraison;
    @NotNull(message = "villeRamassage is required")
    VilleRamassageDto villeRamassage;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
     */
    @Data
    @NoArgsConstructor
    public static class VilleLivraisonDto implements Serializable {
        @NotNull(message = "villeId is required")
        Long villeId;
        @NotBlank(message = "reference is required")
        String reference;
        @NotBlank(message = "nomVille is required")
        String nomVille;
    }

    /**
     * DTO for {@link com.simplon.parametre.model.entity.VilleRamassage}
     */
    @Data
    @NoArgsConstructor
    public static class VilleRamassageDto implements Serializable {
        @NotNull(message = "villeId is required")
        Long villeId;
        @NotBlank(message = "reference is required")
        String reference;
        @NotBlank(message = "nomVille is required")
        String nomVille;
    }
}