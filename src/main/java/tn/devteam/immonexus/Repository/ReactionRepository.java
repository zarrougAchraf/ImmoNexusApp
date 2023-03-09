package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.devteam.immonexus.Entities.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
