package tn.devteam.immonexus.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {
    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    // getters and setters
}
