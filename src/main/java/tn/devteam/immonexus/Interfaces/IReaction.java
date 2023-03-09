package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Reaction;

public interface IReaction {

  public Reaction addReaction(Reaction reaction);

  public Reaction updateReaction(Reaction reaction);

  public void deleteReaction(Long id);

  public Reaction getReaction(Long id);

  //get all reaction
  public Iterable<Reaction> getAllReactions();


}
