package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
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
