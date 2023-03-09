package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectForum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubjectForum;
    private String title;
    private String description;
    @Lob
    private byte[] image;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;
  //  @ManyToOne
  //  @JsonIgnore
  //  Dictionary Dictionary;
    @JsonIgnore
    @OneToMany(
            mappedBy = "subjectForum",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<Reaction> reactions;
}
