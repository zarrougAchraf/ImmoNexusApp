package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Entities.Reaction;
import tn.devteam.immonexus.Services.ReactionService;

@RestController
@AllArgsConstructor
@RequestMapping("/Reaction")
public class ReactionController {

  @Autowired
  ReactionService reactionService;


  @PostMapping("/addReaction")
  public Reaction addReaction(@RequestBody Reaction reaction){
    return reactionService.addReaction(reaction);
  }



}
