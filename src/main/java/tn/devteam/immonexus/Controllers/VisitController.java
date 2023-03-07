package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Visit;
import tn.devteam.immonexus.Interfaces.IVisitService;
import tn.devteam.immonexus.Repository.UserRepository;
import tn.devteam.immonexus.Repository.VisitRepository;
import tn.devteam.immonexus.Services.DistanceService;

import java.util.List;
@CrossOrigin
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/apiVvisit")
public class VisitController {
    @Autowired
    IVisitService ivisitService;


    @Autowired
    VisitRepository visitRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DistanceService distanceService;


    @PostMapping("addVisitWithDistanceCondition")
    public Visit addVisitWithDistanceCondition(@RequestBody Visit visit,
                                               @RequestParam String ville,
                                               @RequestParam String rue,
                                               @RequestParam String codePostal,
                                               @RequestParam double userAdresstLatitude,
                                               @RequestParam double userAdressLongitude) {
        return ivisitService.addVisitWithDistanceCondition( visit,ville, rue, codePostal, userAdresstLatitude, userAdressLongitude);

        }



    @GetMapping("/getAllVisits")
    public List<Visit> getAllVisits() {
        return ivisitService.getAllVisits();
    }

    @GetMapping("/getVisitById/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return ivisitService.getVisitById(id);
    }

    @PostMapping("/addVisit")
    public Visit addVisit(@RequestBody Visit visit) {
        return visitRepository.save(visit);
    }

    @PutMapping("/updateVisit")
    public Visit updateVisit(@RequestBody Visit visit) {
        return ivisitService.updateVisit(visit);
    }

    @DeleteMapping("/deleteVisit/{id}")
    public void deleteVisit(@PathVariable Long id) {
        ivisitService.deleteVisit(id);
    }
}
