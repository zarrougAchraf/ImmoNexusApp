package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.MessageForum;
import tn.devteam.immonexus.Entities.MortgageParameters;
@Repository
public interface MortgageParametersRepository extends JpaRepository<MortgageParameters,Long> {
}
