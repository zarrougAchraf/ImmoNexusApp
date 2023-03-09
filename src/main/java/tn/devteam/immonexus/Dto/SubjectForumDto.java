package tn.devteam.immonexus.Dto;
import lombok.Data;

@Data
public class SubjectForumDto {

    private Long id;
    private String title;
    private String description;
    private byte[] image;


  // private Set<Reaction> reactions;

   private Long NbrLike;
}
