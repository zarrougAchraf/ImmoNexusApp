package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    @Enumerated(EnumType.STRING)
    private ReclamationType type;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReclamation = new Date(System.currentTimeMillis());
    private boolean traitee=false;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

    @OneToOne
    @JsonIgnore
    private ReponseRec reponseReclamation;
}
