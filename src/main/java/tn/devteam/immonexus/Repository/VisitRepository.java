package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.devteam.immonexus.Entities.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}