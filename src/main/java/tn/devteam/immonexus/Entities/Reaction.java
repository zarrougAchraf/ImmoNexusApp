package tn.devteam.immonexus.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Reaction implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private TypeReact typeReact;

private LocalDateTime dateReact;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idSubjectForum")
  private SubjectForum subjectForum;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

}
