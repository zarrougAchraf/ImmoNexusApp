package tn.devteam.immonexus.Entities;

import lombok.*;

import javax.persistence.*;
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
    private String titre;
    private String description;
    @Lob
    private byte[] image;
    private double price;
    private TypeOffer offerType;
    private Integer rate;
    private boolean validity;
    @Embedded
    private Adresse adresse;
    private RealEstateType realEstateType;
    private LocalDate publicationDate;
    private double totalSurface;
    private double coveredArea;



}
