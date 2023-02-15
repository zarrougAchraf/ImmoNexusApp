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
public class Advertising implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAd;
    private String title;
    @Lob
    private byte[] image;
    @Lob
    private byte[] video;
    private String description;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;

}
