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

 /*   @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", this.email);
        attributes.put("firstName", this.firstName);
        attributes.put("lastName", this.lastName);
        attributes.put("profileImageUrl", this.profileImageUrl);
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.role != null) {
            authorities.add(new SimpleGrantedAuthority(this.role.name()));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

      @Override
    public String getName() {
        return this.lastName;
    }*/


    @ManyToMany(mappedBy="user")
    @JsonIgnore
    @ToString.Exclude
    private List<Visit> visit ;

    @JsonIgnore
    @OneToOne(mappedBy="visit")
    @ToString.Exclude
    private VisitPayment ivsitPayment;



}

