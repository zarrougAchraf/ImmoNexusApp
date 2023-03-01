package tn.devteam.immonexus.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
public class ReclamationStatsDTO implements Serializable {

    private ReclamationType type;
    private int count;
    private double percentage;

    // Getters et setters
}
