package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String userId;
    private Integer cin;
    private Integer numeroTel;
    private String adresse;
    private String email;
    private String firstname;
    private String lastName;
    private String username;
    private String password;
    private String profileImageUrl;
    private LocalDate lastLoginDate;
    private LocalDate lastLoginDateDispalay;
    private LocalDate joinDate;
/*
    private String[] roles; // Role_User{read, edit}, Role_Admin
    private String[] authorities;
    */

    private boolean isActive;
    private boolean isNotLocked;

   @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Sponsors> sponsorsList;

     @JsonIgnore
     @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Advertising> advertisingList;

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
