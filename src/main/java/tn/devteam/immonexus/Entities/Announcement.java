package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnounce;
    @NotNull(message = "Le titre ne peut pas être vide")
    private String titre;
    @NotNull(message = "Le titre ne peut pas être vide")
    private Integer LikesNumber;
    private String description;
    private String image;
    private double price;
    @Enumerated(EnumType.STRING)
    private TypeOffer offerType;
    private float rate;

    private boolean validity;
    @Embedded
    private Adresse adresse;
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;
    private LocalDate publicationDate;
    private double totalSurface;
    private double coveredArea;
    private Integer rang;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;


}
