package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.devteam.immonexus.Entities.VisitPayment;

public interface VisitPaymentRepository extends JpaRepository<VisitPayment, Long> {
}