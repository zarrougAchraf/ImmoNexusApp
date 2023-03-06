package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.UserAdress;
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

   /* @PostMapping("addVisitWithDistanceCondition")
    public Visit addVisitWithDistanceCondition(@RequestBody Visit visit, UserAdress userAdress) {
        double distance = distanceService.calculateDistance(visit.getVisitAdress(), userAdress);
        // calculer le prix en fonction de la distance et d'autres critères
        float prix = visit.getPrixVisite();
        if(distance > 50.00) {
            // augmenter le prix de visite de 20%
            prix *= 1.2;
            visit.setPrixVisite(prix);
            ivisitService.addVisit(visit);
        }
        else  {

        ivisitService.addVisit(visit); }

        return visitRepository.save(visit);
    }*/

   /* @PostMapping("addVisitWithDistanceCondition")
    public Visit addVisitWithDistanceCondition(@RequestBody Visit visit, UserAdress userAdress) {
        double distance = distanceService.calculateDistance(visit.getVisitAdress(), userAdress);
        // calculer le prix en fonction de la distance et d'autres critères
        float prix = 0;
        if (distance > 50.00) {
            // augmenter le prix de visite de 20%
            prix *= 1.2;
        }
        visit.setPrixVisite(prix);
        return ivisitService.addVisit(visit);
    }*/

    @PostMapping("addVisitWithDistanceCondition")
    public Visit addVisitWithDistanceCondition(@RequestBody Visit visit,
                                               @RequestParam String ville,
                                               @RequestParam String rue,
                                               @RequestParam String codePostal,
                                               @RequestParam double userAdresstLatitude,
                                               @RequestParam double userAdressLongitude) {
        UserAdress userAdress = new UserAdress(ville, rue, codePostal, userAdresstLatitude, userAdressLongitude);

        System.out.println("userAdress "+userAdress.getUserAdressLongitude()+userAdress.getUserAdresstLatitude());
        double distance = distanceService.calculateDistance(visit.getVisitAdress(), userAdress);
            System.out.println("distance isss"+distance);
        // calculer le prix en fonction de la distance et d'autres critères
        float prix = visit.getVisitPrice();
       if (distance > 50.00) {
            // augmenter le prix de visite de 20%
            prix *= 1.2;
            visit.setVisitPrice(prix);
            System.out.println("visit iss"+ visit.getVisitPrice());


        }
        visit.setVisitPrice(prix);
       //  ivisitService.addVisit(visit);
        return visitRepository.save(visit);
    }




  /*  @GetMapping("/test-calculate-distance")
    public double testCalculateDistance() {
        // Créer un objet de test pour l'adresse de visite
        VisitAdress visitAdress = new VisitAdress();
        visitAdress.setVisitAdressLatitude(48.8566);
        visitAdress.setVisitAdressLongitude(2.3522);

        // Créer un objet de test pour l'adresse de l'utilisateur

        UserAdress userAdress = new UserAdress(userLat, userLon);
        userAdress.setUserAdresstLatitude(45.764);
        userAdress.setUserAdressLongitude(4.8357);

        // Appeler la méthode calculateDistance et renvoyer le résultat
        return distanceService.calculateDistance(visitAdress, userAdress);
    }
*/



  /*  @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);

    }*/

    @GetMapping("/getAllVisits")
    public List<Visit> getAllVisits() {
        return ivisitService.getAllVisits();
    }

    @GetMapping("/getVisitById/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return ivisitService.getVisitById(id);
    }

    @PostMapping("/createVisit")
    public Visit createVisit(@RequestBody Visit visit) {
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
