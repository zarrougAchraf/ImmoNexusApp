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
public class Mortgage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMortage;
    private String loanType;
    private RealEstateType realEstateType;
    private double loanAmount;
    private double downPayment;
    private double interestRate;
    private double duration;
    private LocalDate startDate;
    private LocalDate endDate;
    @Embedded
    private Adresse adresse;
    private double mensuelInvome;


}
