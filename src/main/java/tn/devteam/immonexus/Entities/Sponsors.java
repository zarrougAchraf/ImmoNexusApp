package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sponsors implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;
    private String name;
    private String description;
    private float amountMonthly;
    private String email;
    private String phoneNumber;
    private String logo;
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "sponsor")
    private List<Advertising> advertisingList;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

}
