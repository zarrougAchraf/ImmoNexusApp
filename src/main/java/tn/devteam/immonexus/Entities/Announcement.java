package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private TypeOffer offerType;
    private Integer rate;
    private boolean validity;
    @Embedded
    private Adresse adresse;
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;
    private LocalDate publicationDate;
    private double totalSurface;
    private double coveredArea;


    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

}
