package com.simplon.parametre.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.simplon.parametre.model.entity.Tarif}
 */
@Data
@NoArgsConstructor
public class TarifRequestDto implements Serializable {
    @NotNull(message = "prixLivraison is required")
    Double prixLivraison;
    @NotNull(message = "prixRetour is required")
    Double prixRetour;
    @NotNull(message = "prixRefuse is required")
    Double prixRefuse;
    @NotBlank(message = "delaiLivraison is required")
    String delaiLivraison;
    @NotNull(message = "villeLivraison is required")
    TarifRequestDto.VilleLivraisonDto villeLivraison;
    @NotNull(message = "villeRamassage is required")
    TarifRequestDto.VilleRamassageDto villeRamassage;

    /**
     * DTO for {@link com.simplon.parametre.model.entity.VilleLivraison}
     */
    @Data
    @NoArgsConstructor
    public static class VilleLivraisonDto implements Serializable {
        @NotNull(message = "villeId is required")
        Long villeId;
    }

    /**
     * DTO for {@link com.simplon.parametre.model.entity.VilleRamassage}
     */
    @Data
    @NoArgsConstructor
    public static class VilleRamassageDto implements Serializable {
        @NotNull(message = "villeId is required")
        Long villeId;
    }
}