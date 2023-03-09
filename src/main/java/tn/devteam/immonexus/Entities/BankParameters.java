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
public class BankParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double loanMax;
    private double loanMin;
    private double loanPeriodeMax;
    private double loanPeriodeMin;
    private double douwnPaymentMin;
    private double downPaymentMax;
}
