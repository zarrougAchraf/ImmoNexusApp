package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Claim;
@Repository
public interface ClaimRepository extends JpaRepository<Long, Claim> {
}
