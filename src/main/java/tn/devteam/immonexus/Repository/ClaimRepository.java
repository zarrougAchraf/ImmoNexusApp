package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReclamationType;

import java.util.List;
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Query("select r from Claim r where r.traitee= true")
    List<Claim> findAllTraitees();


    @Query("select r from Claim r where r.traitee= false")
    List<Claim>findAllNonTraitees();

    long countByType(ReclamationType type);

}
