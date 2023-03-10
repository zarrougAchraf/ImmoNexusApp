package tn.devteam.immonexus.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortgageParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMortgageParameters;
    private double propertyTaxRate;
    private double interestRate;
}
