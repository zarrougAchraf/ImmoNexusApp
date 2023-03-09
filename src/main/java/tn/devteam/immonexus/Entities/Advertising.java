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
public class Advertising implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAd;
    private String title;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;


    private String image;

    @Enumerated(EnumType.STRING)
    private Canaux canaux;

    private Long nbrJours;
    private double coutParJour;
    private double nbrVuesCible;
    private double coutParVueCible;
    private double gainPublicitaire;

    private double nbrVuesFinal;





    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Sponsors sponsor;





}
