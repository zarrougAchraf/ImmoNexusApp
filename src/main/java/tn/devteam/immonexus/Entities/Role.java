package tn.devteam.immonexus.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String description;

}
