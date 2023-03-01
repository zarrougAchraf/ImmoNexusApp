package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReclamationType;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.exception.ExceptionHandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Reclamation")
public class ClaimController extends ExceptionHandling {
    @Autowired
    private IClaimService iClaimService;


    @PostMapping("/add/{userId}")
    //localhost:8080/WomenEmpowerment/Reclamation/add/
    public Claim addReclamation(@RequestBody Claim r,@PathVariable("userId") Long id) {
        return iClaimService.addReclamation(r,id);
    }
    @GetMapping("/getAll")
    public List<Claim> getAllReclamations(){
        return iClaimService.getAllReclamations();
    }
    @GetMapping("/getReclamationsTraitees")
    public List<Claim> getReclamationsTraitees() {
        return iClaimService.getReclamationsTraitees();

    }

    @GetMapping("/getReclamationsNonTraitees")
    public List<Claim> getReclamationsNonTraitees() {
        return iClaimService.getReclamationsNonTraitees();

    }
    @GetMapping("/getReclamationsByClient/{idUser}")
    public List<Claim> getReclamationsByClient(@PathVariable("idUser") Long idUser) {
        return iClaimService.getReclamationsByUser(idUser);

    }
    @PutMapping("/marquerTraitee/{idReclamation}")
    public void marquerTraitee(@PathVariable("idReclamation") Long idReclamation)
    {
        iClaimService.marqueTraitee(idReclamation);
    }
    @DeleteMapping("/delete/{idReclamation}")
    public void deleteReclamation(@PathVariable("idReclamation") Long id) {
        iClaimService.deleteReclamation(id);
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Long> getTotalNumberOfClaims() {
        return ResponseEntity.ok(iClaimService.getNumberOfClaims());
    }

    @GetMapping("/reclamation/stats")
    public Map<String, Long> getReclamationStats() {
        Map<String, Long> stats = new HashMap<>();
        for (ReclamationType type : ReclamationType.values()) {
            long count = iClaimService.countReclamationsByType(type);
            stats.put(type.toString(), count);
        }
        return stats;
    }
}
