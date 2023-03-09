package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, updatable = false)
    @Size(min = 5, max = 20, message = "L'identifiant doit contenir entre 5 et 20 caractères")
    private String userId;
    @Column(nullable = false, length = 20)
    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String firstName;
    @Column(nullable = false ,length = 20)
    private String lastName;
    @Column(nullable = false ,length = 20)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(nullable = false, unique = true, length = 45)
    @Email(message = "L'adresse e-mail n'est pas valide")
    private String email;
    @URL(message = "L'URL de l'image de profil n'est pas valide")
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private String role; //ROLE_USER{ read, edit }, ROLE_ADMIN {delete}
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;



    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<ReponseRec> ReponsRec;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Sponsors> sponsorsList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<SubjectForum> subjectForumList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Announcement> announcementList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Visit> visitList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Scraping> scrapingList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany
    private List<Rating> ratingList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Claim> claimList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Mortgage> mortgageList;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany
    private List<GroupAuction> groupAuctionList;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Affordability> affordabilityList;
}
