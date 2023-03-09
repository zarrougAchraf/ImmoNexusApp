package tn.devteam.immonexus.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitAdress implements Serializable {
    private String ville;
    private String rue;
    private String codePostal;
  //  private Coordinates coordinates;
  private Double visitAdressLatitude;

    private Double visitAdressLongitude;

}