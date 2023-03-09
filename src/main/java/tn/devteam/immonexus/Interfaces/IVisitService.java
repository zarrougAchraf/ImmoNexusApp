package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Visit;

import java.util.List;

public interface IVisitService {
    public List<Visit> getAllVisits();

    public Visit getVisitById(Long id);

    public Visit addVisit(Visit visit);

    public Visit updateVisit(Visit visit);

    public void deleteVisit(Long id);

    public Visit addVisitWithDistanceCondition( Visit visit, String ville, String rue, String codePostal, double userAdresstLatitude, double userAdressLongitude);


}
