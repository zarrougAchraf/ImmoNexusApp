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


 /*   @Override
    public Visit createVisit(Visit visit, UserAdress userAdress) {
        return null;
    }*/

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

    @Override
    public Visit addVisitWithDistanceCondition( Visit visit, String ville, String rue, String codePostal, double userAdresstLatitude, double userAdressLongitude) {
        UserAdress userAdress = new UserAdress(ville, rue, codePostal, userAdresstLatitude, userAdressLongitude);
        double distance = distanceService.calculateDistance(visit.getVisitAdress(), userAdress);

        float price = visit.getVisitPrice();
        if (distance > 50.00) {
            // increase the visit price by 20%
            price *= 1.2;
            visit.setVisitPrice(price);
            System.out.println("visit iss"+ visit.getVisitPrice());


        }
        visit.setVisitPrice(price);
        //  ivisitService.addVisit(visit);
        return visitRepository.save(visit);
    }




}

