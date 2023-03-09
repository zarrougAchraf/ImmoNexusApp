package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Visit;
import tn.devteam.immonexus.Entities.VisitPayment;
import tn.devteam.immonexus.Interfaces.IVisitPayment;
import tn.devteam.immonexus.Repository.VisitPaymentRepository;

import java.util.List;

@Service
@Slf4j
public class VisitPaymentService  implements IVisitPayment {


    @Autowired
    VisitPaymentRepository visitPaymentRepository;


    @Override
    public List<VisitPayment> getAllVisitPayment() {
        return visitPaymentRepository.findAll();
    }

    @Override
    public VisitPayment getVisitPaymentById(Long id) {
        return visitPaymentRepository.findById(id).orElse(null);
    }

    @Override
    public VisitPayment addVisitPayment(VisitPayment visitPayment) {
        return  visitPaymentRepository.save(visitPayment);
    }

    @Override
    public VisitPayment updateVisitPayment(VisitPayment visitPayment) {
        VisitPayment existingPayment = visitPaymentRepository.findById(visitPayment.getIdPayment()).orElse(null);
        if (existingPayment == null) {
            return null;
        }
        visitPayment.setAmount(visitPayment.getAmount());
        visitPayment.setPaymentDate(visitPayment.getPaymentDate());
        visitPayment.setPaymentMethod(visitPayment.getPaymentMethod());
        visitPayment.setPaymentStatus(visitPayment.getPaymentStatus());
        visitPayment.setVisit(visitPayment.getVisit());

        return visitPaymentRepository.save(visitPayment);
        }

    @Override
    public void deleteVisitPayment(Long id) {

        visitPaymentRepository.deleteById(id);

    }
}
