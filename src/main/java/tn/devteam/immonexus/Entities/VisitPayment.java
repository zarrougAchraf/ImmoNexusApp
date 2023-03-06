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
    public class VisitPayment implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idPayment;

        private double amount;

        private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Visit visit;


}
