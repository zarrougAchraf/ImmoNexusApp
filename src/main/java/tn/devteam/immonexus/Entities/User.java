package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String profileImageUrl;

    @Embedded
    private UserAdress userAdress;

  @Enumerated(EnumType.STRING)
   private Roles role;

    public User(String email, String name, String pictureUrl) {

        this.email=email;
        this.lastName=name;
        this.profileImageUrl=pictureUrl;
    }



    @ManyToMany(mappedBy="user")
    @JsonIgnore
    @ToString.Exclude
    private List<Visit> visit ;





}

