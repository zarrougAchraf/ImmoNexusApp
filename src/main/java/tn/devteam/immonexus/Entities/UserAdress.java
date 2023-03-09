package tn.devteam.immonexus.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAdress implements Serializable {
    private String ville;
    private String rue;
    private String codePostal;
   // @Transient
    //private Coordinates coordinates;
   private Double userAdresstLatitude;
    private Double userAdressLongitude;


    public UserAdress(double userLat, double userLon) {

        this.userAdresstLatitude=userLat;
        this.userAdressLongitude=userLon;

    }
}

