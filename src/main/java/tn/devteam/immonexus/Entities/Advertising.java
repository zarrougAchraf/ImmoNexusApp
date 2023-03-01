package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

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
   /* @Lob
    private byte[] image;
    @Lob
    private byte[] video;*/
   @Enumerated(EnumType.STRING)
   private PubType type;

  /*  @Lob
    private byte[] image;*/


    private String picture;
    private String description;
    private String nom;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    @Enumerated(EnumType.STRING)
    private Canaux canaux;


    private double nbrVuesCible;
    private double nbrVuesFinal;




    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "advertising")
    private PopulationCible  populationCible;
@JsonIgnore
@ToString.Exclude
    @ManyToOne
    private Sponsors sponsor;


    @OneToOne
    @JsonIgnore

    private FileDB fileDB;



}
