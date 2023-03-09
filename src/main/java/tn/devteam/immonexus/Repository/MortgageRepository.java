package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Mortgage;
@Repository
public interface MortgageRepository extends JpaRepository<Mortgage,Long> {
    @Query("select avg(m.loanAmount) From Mortgage m where m.loanType= :loanType")
    Double getAvreageLoanAmountByType(@Param("loanType") String loanType);
}
