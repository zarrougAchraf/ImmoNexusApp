package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Advertising;
@Repository
public interface AdevertisingRepository extends JpaRepository<Advertising, Long> {
}
