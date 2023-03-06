package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.UserAdress;
import tn.devteam.immonexus.Entities.Visit;
import tn.devteam.immonexus.Interfaces.IVisitService;
import tn.devteam.immonexus.Repository.VisitRepository;

import java.util.List;

@Service
@Slf4j
public class visitService implements IVisitService {
    @Autowired
    VisitRepository visitRepository;

    @Autowired
    private DistanceService distanceService;

    @Override
    public Visit createVisit(Visit visit, UserAdress userAdress) {
        return null;
    }

    @Override
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);    }

    @Override
    public Visit updateVisit(Visit visit) {
        Visit existingVisit = visitRepository.findById(visit.getIdVisit()).orElse(null);
        if (existingVisit == null) {
            return null;
        }
        existingVisit.setVisitDate(visit.getVisitDate());
        existingVisit.setVisitAdress(visit.getVisitAdress());
        existingVisit.setDuration(visit.getDuration());
        existingVisit.setVisitType(visit.getVisitType());
        existingVisit.setVisitStatus(visit.getVisitStatus());
        existingVisit.setUser(visit.getUser());
        return visitRepository.save(existingVisit);    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);


    }

  /*
    @RestController
    @RequestMapping("/visits")
    public class VisitService {
        @Autowired
        private DistanceService distanceService;

      @PostMapping
        public Visit createVisit(@RequestBody Visit visit) {
            double userLatitude=48.8566;
            double userLongitude=2.3522;
            Coordinates userCoordinates = new Coordinates();
            double distance = distanceService.calculateDistance(userCoordinates, visit.getVisitAdress().getCoordinates());
            // calculer le prix en fonction de la distance et d'autres critères
            float prix = visit.getPrixVisite();
            if(distance > 50) {
                // augmenter le prix de visite de 20%
                prix *= 1.2;
            }
            visit.setPrixVisite(prix);
            return visitRepository.save(visit);
        }}
    }*/


}

