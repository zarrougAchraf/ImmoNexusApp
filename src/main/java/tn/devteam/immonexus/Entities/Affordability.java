package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Affordability implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAffordability;


    private double creditScore;

    private double loanAmount;
    private long loanTerme;

    private double monthlyIncome;

    private double monthlyDebtPayments;

    private double homeownersInsuranceRate;
    private double otherMonthlyExpenses;
    @OneToOne
    private MortgageParameters mortgageParameters;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user ;



}
