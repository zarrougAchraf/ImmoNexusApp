package tn.devteam.immonexus.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.VisitPayment;
import tn.devteam.immonexus.Services.VisitPaymentService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/apiVisitPayment")
public class VisitPaymentController {

    @Autowired
    private VisitPaymentService visitPaymentService;

    @GetMapping("/all")
    public ResponseEntity<List<VisitPayment>> getAllVisitPayments() {
        List<VisitPayment> visitPayments = visitPaymentService.getAllVisitPayment();
        return new ResponseEntity<>(visitPayments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitPayment> getVisitPaymentById(@PathVariable("id") Long id) {
        VisitPayment visitPayment = visitPaymentService.getVisitPaymentById(id);
        if (visitPayment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visitPayment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<VisitPayment> addVisitPayment(@RequestBody VisitPayment visitPayment) {
        VisitPayment newVisitPayment = visitPaymentService.addVisitPayment(visitPayment);
        return new ResponseEntity<>(newVisitPayment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<VisitPayment> updateVisitPayment(@RequestBody VisitPayment visitPayment) {
        VisitPayment updatedVisitPayment = visitPaymentService.updateVisitPayment(visitPayment);
        if (updatedVisitPayment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedVisitPayment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitPayment(@PathVariable("id") Long id) {
        visitPaymentService.deleteVisitPayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
