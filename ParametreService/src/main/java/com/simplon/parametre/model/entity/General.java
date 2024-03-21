package com.simplon.parametre.model.entity;

import com.simplon.parametre.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "general")
public class General {
    @Id
    @Column(name = "generalId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general_sequence")
    @SequenceGenerator(name = "general_sequence", sequenceName = "general_sequence", allocationSize = 1)
    private Long generalId;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "favicon", nullable = false)
    private String favicon;

    @Column(name = "nomEntreprise", nullable = false)
    private String nomEntreprise;

    @Column(name = "webSite", nullable = false)
    private String webSite;

    @Column(name = "adresseEntreprise", nullable = false)
    private String adresseEntreprise;

    @Column(name = "telephoneEntreprise", nullable = false)
    private String telephoneEntreprise;

    @Email(message = "Email should be valid")
    @Column(name = "emailEntreprise", nullable = false)
    private String emailEntreprise;

    @Column(name = "monnaieApplication", nullable = false)
    private String monnaieApplication;

    private static General instance;
    private static boolean isInitialized = false;

    private General() {
    }

    public static General getInstance() {
        if (instance == null) {
            instance = new General();
        }
        return instance;
    }

    public void initialize() {
        if (!isInitialized) {
            instance.setLogo("logossss.png");
            instance.setFavicon("favicon.png");
            instance.setNomEntreprise("Simplon");
            instance.setWebSite("https://www.simplon.co/");
            instance.setAdresseEntreprise("Rue de la République, 13002 Marseille");
            instance.setTelephoneEntreprise("04 91 91 91 91");
            instance.setEmailEntreprise("simplon@gmail.com");
            instance.setMonnaieApplication("Dhs");
            isInitialized = true;
        } else {
            System.out.println("Instance is already initialized");
        }
    }
}