package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Visit;
import tn.devteam.immonexus.Entities.VisitPayment;

import java.util.List;

public interface IVisitPayment {

    public List<VisitPayment> getAllVisitPayment();

    public VisitPayment getVisitPaymentById(Long id);

    public VisitPayment addVisitPayment(VisitPayment visit);

    public VisitPayment updateVisitPayment(VisitPayment visit);

    public void deleteVisitPayment(Long id);






}
