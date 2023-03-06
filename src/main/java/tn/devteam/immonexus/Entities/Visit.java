package tn.devteam.immonexus.Entities;

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
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVisit;
    private LocalDate visitDate;
  //
  // @Embedded
  // private VisitAdress visitAdress;
    private double duration;
    @Enumerated(EnumType.STRING)
    private VisitType visitType;

    @Enumerated(EnumType.STRING)
    private VisitStatus visitStatus;

    private float visitPrice;

    @Embedded
    private VisitAdress visitAdress;


    @ManyToMany(cascade =CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<User> user ;





}
